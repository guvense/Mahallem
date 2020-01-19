import IMessage from './imessage'
import { MessageType } from './message-type'
import TypingMessage from './chat/cts-typing'
import IApp from '../iapp'
import MessageContent from './chat/cts-message'


export function MessageConverter(msg: IMessage, app: IApp): IMessage {
    switch (msg.type) {

        case MessageType.cts_typing:
            return new TypingMessage(msg, app)
        case MessageType.cts_message:
            return new MessageContent(msg,app)
            
    }
}

export const ConvertMessage = MessageConverter
