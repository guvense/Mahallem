import User from "./user"


export default class ClientInfo {
    public id: string
    public meta: any
    public onlineAt: Date
    public user: User

    constructor(socket: any) {
        this.user = socket.request.user
        this.meta = socket.handshake
        this.id = socket.id
        this.onlineAt = new Date()
    }

    get isAdmin(): boolean {
        return this.meta.query.admin === 'true'
    }
}
