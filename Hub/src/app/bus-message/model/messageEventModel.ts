import { MessageFieldType } from "./messageFieldType"
import { MessageState } from "./messageState"


export default class MessageEventModel {

    public fromUserId?: string
    public toUserId?: string
    public content?: string
    public image?: string
    public messageType?: string
    public clientId?: string
    public messageState?: MessageState
    public messageId?: string
    }