import ClientInfo from './server/client-info'
import IMessage from './message/imessage'
import BusMessage from './bus-message/bus-message'

export default interface IApp {
    connect(client: ClientInfo): void
    disconnect(client: ClientInfo): void
    receive(msg: IMessage, fn: (param: any) => void): any
    send(msg: IMessage): any
    getClientInfo(id: string): ClientInfo
    sendBus(msg: BusMessage): any
}
