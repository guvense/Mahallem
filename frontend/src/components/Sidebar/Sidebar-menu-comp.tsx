import React, { Fragment, useState } from "react";
import { Link } from "react-router-dom";

const SidebarMenu: React.FC = () => {

    let [sidebarMenuVisibility, setSidebarMenuVisibility] = useState(false);

    function changeSidebarMenuVisibility() {
        setSidebarMenuVisibility(!sidebarMenuVisibility);
    }

    function getCollapseClass() {
        return (sidebarMenuVisibility) ? "" : "collapsed";
    }

    return (
        <Fragment>
            <div className="toggle-area">
                <button className="btn btn-primary toggle-button" onClick={() => changeSidebarMenuVisibility()}>
                    <i className="fas fa-bolt"></i>
                </button>
            </div>

            <ul className={`navbar-nav bg-gradient-primary-green sidebar sidebar-dark accordion ${getCollapseClass()}`}
                id="collapseMenu">

                <a className="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                    <div className="sidebar-brand-icon icon-green rotate-n-15">
                        <i className="fas fa-bolt"></i>
                    </div>
                    <div className="sidebar-brand-text mx-3">MAHALLEM <sup>App</sup></div>
                </a>

                <hr className="sidebar-divider my-0" />

                <li className="nav-item active">

                    <Link className="nav-link" to="Home">
                        <i className="fas fa-fw fa-tachometer-alt"></i>
                        <span>Homepage</span>
                    </Link>
                </li>

                <hr className="sidebar-divider my-0" />

                <li className="nav-item">
                    <Link className="nav-link" to={`/users`}>
                        <i className="fas fa-fw fa-user"></i>
                        <span>Users</span>
                    </Link>
                </li>

                <hr className="sidebar-divider d-none d-md-block" />
            </ul>
        </Fragment>
    );
};

export default SidebarMenu;
