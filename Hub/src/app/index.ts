import * as Redis from 'ioredis'
import { Server } from './server/'
import IApp from './iapp'
import ClientInfo from './server/client-info'
import IMessage from './message/imessage'
import MessageHelper from './message/message-helper'
import { MessageType } from './message/message-type'
import { ManageClientMessage } from './message/manage/manage-client'
import Message from './message/message'
import BusMessage from './bus-message/bus-message'
import EventHandler from './event-handler/event-handler'
import Dummy from './event-handler/dummy'

export default class App implements IApp {

    private server: Server
    private redis: Redis.Redis
    private redisPub: Redis.Redis
    private clients: ClientInfo[]
    private handlers: EventHandler[]

    public static bootstrap() {
        return new App()
    }

    private constructor() {
        this.init()
    }

    private async init() {
        this.clients = []
        this.handlers  = []
        this.redis = new Redis({ host: 'localhost' })
        this.redisPub = new Redis({ host: 'localhost' })

        this.registerSubscibers()
        this.server = new Server(this)
    }

    private registerSubscibers() {
        this.handlers.push(new Dummy(this))
        //  ...

        let a : string [] = []
        this.redis.subscribe(this.handlers.map((h) => h.channel))

        this.redis.on('message', (channel: string, message: any) => {
            this.handlers.forEach((l)=> console.log(l.channel.toString()));
            
            const handler = this.handlers.find((h) => h.channel.toString() === channel)
            if (handler) {
                const obj = JSON.parse(message)
                handler.read(obj.Client, obj)
                // handler.read(message)
            }
        })
    }

    public connect(client: ClientInfo) {
        console.log('client connect', client)
        this.notify(MessageHelper.Create(MessageType.notify_connect, client))

        this.clients.push(client)
        //  this.notify(MessageHelper.Create(MessageType.notify_connect, client))
        //  redis send client connect this.clients.push(client)

        //this.sendBus(new ConnectionEvent(ConnectionEventType.online, client))

        if (client.isAdmin) {
            this.send(new ManageClientMessage(this.clients, client))
        }
    }

    public sendBus(msg: BusMessage) {
        this.redisPub.publish(msg.channel, msg.ToJson())
    }

    public disconnect(client: ClientInfo) {
        console.log('disconnect', client)
        //this.sendBus(new ConnectionEvent(ConnectionEventType.offline, client))

        const index = this.clients.findIndex((c) => c.id == client.id)
        if (index > -1) {
            this.clients.splice(index, 1)
            this.notify(MessageHelper.Create(MessageType.notify_disconnect, client))
        }
    }

    public async receive(msg: Message, fn: (param: any) => void) {
        console.log('message', msg)
        this.notify(MessageHelper.Create(MessageType.notify_receive, msg.client, msg.clone()))

        msg.read((param: any) => {
            if (fn) {
                fn(param)
            }
        })
    }

    public async send(msg: Message) {
        this.server.io
            .to(msg.client.id)
            .emit('message', {
                content: msg.content,
                error: msg.error,
                type: msg.type,
            })
        this.notify(MessageHelper.Create(MessageType.notify_send, msg.client, msg.clone()))
    }

    public getClientInfo(id: string): ClientInfo {
        return this.clients.find((client) => client.user && client.user.id == id)
    }

    private async notify(msg: IMessage) {
        for (const admin of this.clients.filter((c) => c.isAdmin &&
                (msg.client ? c.id != msg.client.id : true))) {
            this.server.io.to(admin.id).emit('message', msg)
       }
    }

}
