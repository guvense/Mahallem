import { IUser } from "../models/user.interface";

export const ADD_ADMIN: string = "ADD_ADMIN";
export const REMOVE_ADMIN: string = "REMOVE_ADMIN";
export const FAV_USER: string = "FAV_USER";

export function addAdmin(user: IUser): IAddAdminActionType {
    return { type: ADD_ADMIN, user: user };
}

export function removeAdmin(user: IUser): IRemoveAdminActionType {
    return { type: REMOVE_ADMIN, user: user };
}

export function setUserFav(user: IUser): ISetUserFavActionType {
    return {type: FAV_USER, user: user}
}

interface IAddAdminActionType { type: string, user: IUser };
interface IRemoveAdminActionType { type: string, user: IUser };
interface ISetUserFavActionType { type: string, user: IUser };
