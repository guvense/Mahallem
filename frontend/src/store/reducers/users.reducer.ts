import { IUserState, IActionBase } from "../models/root.interface";
import { ADD_ADMIN, REMOVE_ADMIN, FAV_USER } from "../actions/users.action";

const initialState: IUserState = {
    users: [
        { id: 1, firstName: "John", lastName: "Smith", email: "jsmith@em.pl", },
        { id: 2, firstName: "Jannice", lastName: "Bing", email: "ohmy@fr.pl" },
        { id: 4, firstName: "Guven", lastName: "Seckin", email: "ohmy@fr.pl" }
    ],
    admins: [
        { id: 3, firstName: "Jannet", lastName: "Crock", email: "jcrock@em.pl" },
    ],
    favs: []
};

function userReducer(state: IUserState = initialState, action: IActionBase): IUserState {
    switch (action.type) {
        case ADD_ADMIN: {
            return { ...state, users: state.users.filter(x=>x.id !== action.user.id), admins: [...state.admins, action.user]};
        }
        case REMOVE_ADMIN: {
            return { ...state, admins: state.admins.filter(x=>x.id !== action.user.id), users: [...state.users, action.user]};
        }
        case FAV_USER: {

            if(!state.favs.includes(action.user))
            return { ...state, favs: [...state.favs, action.user]};
        }
        default:
            return state;
    }
}

export default userReducer;