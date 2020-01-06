import IMessage from './imessage'
import { MessageType } from './message-type'
import TypingMessage from './chat/cts-typing'
import IApp from '../iapp'


export function MessageConverter(msg: IMessage, app: IApp): IMessage {
    switch (msg.type) {

        case MessageType.cts_typing:
            return new TypingMessage(msg, app)
    }
}

export const ConvertMessage = MessageConverter
