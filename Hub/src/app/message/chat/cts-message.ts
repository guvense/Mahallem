import Message from "../message";
import { MessageType } from "../message-type";
import MessageHelper from "../message-helper";
import MessageEventModel from "../../bus-message/model/messageEventModel";
import { MessageFieldType } from "../../bus-message/model/messageFieldType";
import MessageEventSender from "../../bus-message/messageEventSender";

export default class MessageContent extends Message {
  public type = MessageType.cts_message;
  public content: any;

  public async read(fn: (param: any) => void) {
    const toClient = this.app.getClientInfo(this.content.toUserId);
    if (!toClient) {
      return super.read(fn);
    }

    var messageContent: MessageEventModel;

    if (this.content.messageType == MessageFieldType.Text) {
        messageContent = {
        fromUserId: this.client.user.id,
        toUserId: this.content.toUserId,
        content: this.content.messageContent
      };
    } else if (this.content.messageType == MessageFieldType.Image) {
        messageContent = {
        fromUserId:  this.client.user.id,
        toUserId: this.content.toUserId,
        image: this.content.image
      };
    }

    console.log("gelen -> "+ messageContent.fromUserId + messageContent.toUserId+ messageContent.content);

    this.app.sendBus(new MessageEventSender(toClient, messageContent));
  }
}
