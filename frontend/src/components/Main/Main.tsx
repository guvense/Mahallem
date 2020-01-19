import React, { Fragment } from "react";
import SidebarMenu from "../Sidebar/Sidebar-menu-comp";
import HeaderMenu from "../Header/HeaderMenu";
import { Switch, Route } from "react-router";
import Users from "../Users/Users";
import Home from "../Home/Home";
import Notifications from "../../common/components/Notification";

const Admin: React.FC = () => {

  return (
    <Fragment>
      <Notifications />
      <SidebarMenu />
      <div id="content-wrapper" className="d-flex flex-column">
        <div id="content">
          <HeaderMenu />
          <div className="container-fluid">
            <Switch>
              <Route path={`/users`}><Users /></Route>
              <Route path="/"><Home /></Route>
            </Switch>
          </div>
        </div>
      </div>
    </Fragment>
  );
};

export default Admin;
