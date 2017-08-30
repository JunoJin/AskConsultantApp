import React, { Component } from 'react';
import MessageView from "./MessageView";
import MessengerTextComponent from "./MessengerTextComponent";
import NewConversationPage from "./NewConversationPage";


class MessageWindow extends Component {
    render(){
        return (
            <div id="message_windowe">
                <MessageView/>
                <MessengerTextComponent/>
                <NewConversationPage/>
            </div>
        )
    }
}

export default MessageWindow;