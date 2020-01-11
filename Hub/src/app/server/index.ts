import * as config from 'config'
import * as http from 'http'
import * as express from 'express'
import * as socketIo from 'socket.io'
import * as redisAdapter from 'socket.io-redis'
import IApp from '../iapp'
import ClientInfo from './client-info'
import * as Redis from 'ioredis'
import * as request from 'request-promise-native'
import { ConvertMessage } from '../message/message-converter'
import IMessage from '../message/imessage'
import * as _ from 'lodash'
import User from './user'
import ResultModel from './result-model'

export class Server {

    private httpServer: http.Server
    private express: express.Express
    private ioServer: SocketIO.Server
    private port: number

    constructor(private app: IApp) {
        this.express = express()
        this.httpServer = http.createServer(this.express)
        this.port = config.get('port') as number
        const adapter = redisAdapter({
            pubClient: new Redis({ host: 'localhost' }),
            subClient: new Redis({ host: 'localhost' }),
        })
        this.ioServer = socketIo(this.httpServer, {
            path: '/mahallem',
            adapter,
           
        })
        this.listen()
    }

    // public get http(): http.Server {
    //     return this.httpServer
    // }

    public get io(): SocketIO.Server {
        return this.ioServer
    }

    private listen() {
        this.ioServer.use(async (socket, next) => {

            console.log("Socket in");
            console.log(`${config.get('apiUrl')}/user`);
            
            
            //  tslint:disable-next-line
            let token = socket.handshake.headers['Authorization']
            if (!token) {
                token = socket.handshake.query.authorization
            }
            try {
                const response = await request.get(`${config.get('apiUrl')}:8081/api/v1/user`, {
                    headers: {
                        Authorization: token,
                    },
                })
                const result: ResultModel = JSON.parse(response)
                console.log(result);
                
                if (result) {
                    
                     const user: User = _.assign(new User(), _.pick(result.data, _.keys(new User())))
                     console.log(user);
                     
                     socket.request.user = user
                     if (this.app.getClientInfo(user.id)) {
                         console.log('User Exist---->>', user.id)
                         return next(new Error('authentication error'))
                    }
                     return next()
                }
            } catch (error) {
                console.log(error)
            }

            return next(new Error('authentication error'))
        })
        this.httpServer.listen(this.port, () => {
            console.log('http server listen', this.port)
            this.ioServer.on('connect', this.connect)
        })
    }

    private connect = (socket: any) => {
        const ci: ClientInfo = new ClientInfo(socket)
        this.app.connect(ci)

        socket.use((packet: any, next: any) => {
            if (!packet) {
                const err = new Error('packet not valid')
                //  Raven.captureException(err, { extra: { packet, ci }})
                return next(err)
            }
            if (packet[0] !== 'message') {
                const err = new Error('message not valid')
                //  Raven.captureException(err, { extra: { packet, ci }})
                return next(err)
            }
            if (!packet[1].type) {
                const err = new Error('message type not valid')
                //  Raven.captureException(err, { extra: { packet, ci }})
                return next(err)
            }

            packet[1].client = ci
            packet[1] = ConvertMessage(packet[1] as IMessage, this.app)
            if (!packet[1]) {
                const err = new Error('message package not valid')
                //  Raven.captureException(err, { extra: { packet, ci }})
                return next(err)
            }
            return next(null, packet)
        })

        socket.on('message', (msg: any, fn: (param: any) => void) => {
            this.app.receive(msg, fn)
        })

        socket.on('disconnect', () => {
            this.app.disconnect(ci)
        })
    }
}
