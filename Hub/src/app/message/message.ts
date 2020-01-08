import * as _ from 'lodash'
import IMessage from './imessage'
import ClientInfo from '../server/client-info'
import { MessageType } from './message-type'
import IApp from '../iapp'

export default abstract class Message implements IMessage {
    public client: ClientInfo
    public type: MessageType
    public content: any
    public error: string[]
    public app: IApp

    constructor(obj?: IMessage, app?: IApp) {
        if (obj) {
            this.type = obj.type
            this.content = obj.content
            this.client = obj.client
            this.error = obj.error
        }
        this.app = app
    }

    public async read(fn: (param: any) => void): Promise<void> {
        fn(null)
    }

    public clone() {
        return _.pick(this, [
            'client', 'type', 'content', 'error',
        ])
    }
}
