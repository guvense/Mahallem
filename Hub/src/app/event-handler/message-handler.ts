import EventHandler from "./event-handler";
import IApp from "../iapp";
import { BusChannel } from "../event-bus/bus-channel";
import ClientInfo from "../server/client-info";
import MessageEventModel from "../bus-message/model/messageEventModel";
import MessageHelper from "../message/message-helper";
import { MessageType } from "../message/message-type";

export default class MessageHandler extends EventHandler {
  constructor(private app: IApp) {
    super(BusChannel.MESSAGE_CONTENT_REST_TO_HUB, app);
  }

  public async read(client: ClientInfo, data: any) {
    console.log("data" + data);

    if (data) {
      const toClientSocket = this.app.getClientInfo(
        data.eventMessageModel.toUserId
      );

      console.log("data" + data.eventMessageModel.toUserId);
      console.log("data2" + toClientSocket);

      if (toClientSocket) {
        const message: MessageEventModel = {
          fromUserId: data.eventMessageModel.fromUserId,
          content: data.eventMessageModel.content,
          messageId: data.eventMessageModel.messageId,
          messageState: data.eventMessageModel.messageState,
          messageType: data.eventMessageModel.messageType
        };

        this.app.send(
          MessageHelper.Create(MessageType.stc_message, toClientSocket, message)
        );
      }
    }
  }
}
