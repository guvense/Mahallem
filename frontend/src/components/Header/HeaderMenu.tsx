import React from "react";
import HeaderMenuAccount from "./HeaderMenuAccount";
import "./HeaderMenu.css";
import { useSelector } from "react-redux";
import { IStateType, IRootPageStateType } from "../../store/models/root.interface";

const HeaderMenu: React.FC = () => {
  const page: IRootPageStateType = useSelector((state: IStateType) => state.root.page);

  return (
    <nav className="navbar navbar-expand navbar-light bg-custom-dark topbar mb-4 static-top shadow">
      <ol className="breadcrumb dark-breadcrumb">
        <li className="breadcrumb-item"><a href="# ">{page ? page.area : null}</a></li>
        <li className="breadcrumb-item"><a href="# ">{page ? page.subArea : null}</a></li>
      </ol>

      <ul className="navbar-nav ml-auto">
        <div className="topbar-divider d-none d-sm-block"></div>
        <HeaderMenuAccount />
      </ul>
    </nav>
  );
};

export default HeaderMenu;
