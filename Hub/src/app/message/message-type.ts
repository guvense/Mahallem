export enum MessageType {
    cts_message = 1,
    cts_message_callback,
    cts_read_messages,
    cts_typing,
    cts_sent_messages,

    cts_profile_view,
    cts_permission_request,

    stc_message,
    stc_typing,

    stc_error_messages,

    stc_favoriteOnlineNotification,
    stc_profile_view,
    stc_add_favoriteNotification,
    stc_first_messageNotification,

    stc_user_status,

    //  ...
    notify_connect = 900,
    notify_disconnect,
    notify_send,
    notify_receive,

    stc_manage_connect,
    stc_manage_transport,
    stc_auth_error,
}
