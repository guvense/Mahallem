import EventHandler from './event-handler'
import { BusChannel } from '../event-bus/bus-channel'
import ClientInfo from '../server/client-info'
import IApp from '../iapp'
import ConnectionEvent from '../bus-message/connectionEvent'

export default class Dummy extends EventHandler {

    constructor(private app: IApp) {
        super(BusChannel.A, app)
    }

    public async read(client: ClientInfo, data: any) {
        console.log(data);
        
        if (data) {

       //this.app.sendBus(new ConnectionEvent("selam",client))
      
        }

    }
}
