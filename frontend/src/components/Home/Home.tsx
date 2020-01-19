import React, { Fragment, Dispatch } from "react";
import { useDispatch, useSelector } from "react-redux";
import { updateCurrentPath } from "../../store/actions/root.actions";
import TopCard from "../../common/components/TopCard";
import { IProductState, IStateType } from "../../store/models/root.interface";
import { IOrder } from "../../store/models/order.interface";

const Home: React.FC = () => {
  const products: IProductState = useSelector((state: IStateType) => state.products);
  const numberItemsCount: number = products.products.length;
  const totalPrice: number = products.products.reduce((prev, next) => prev + ((next.price * next.amount) || 0), 0);
  const totalProductAmount: number = products.products.reduce((prev, next) => prev + (next.amount || 0), 0);

  const orders: IOrder[] = useSelector((state: IStateType) => state.orders.orders);
  const totalSales: number = orders.reduce((prev, next) => prev + next.totalPrice, 0);
  const totalOrderAmount: number = orders.reduce((prev, next) => prev + next.amount, 0);

  const dispatch: Dispatch<any> = useDispatch();
  dispatch(updateCurrentPath("home", ""));

  return (
    <Fragment>
      <h1 className="h3 mb-2 text-gray-800">Home Page</h1>
      <p className="mb-4">Summary and overview of Mahallem app</p>

      <div className="row">
        <TopCard title="PRODUCT COUNT" text={`${numberItemsCount}`} icon="box" class="primary" />
        <TopCard title="PRODUCT AMOUNT" text={`${totalProductAmount}`} icon="warehouse" class="danger" />
        <TopCard title="SUMMARY PRICE" text={`$${totalPrice}`} icon="dollar-sign" class="success" />
      </div>

      <div className="row">
        <TopCard title="SALES" text={totalSales.toString()} icon="donate" class="primary" />
        <TopCard title="ORDER AMOUNT" text={totalOrderAmount.toString()} icon="calculator" class="danger" />
      </div>

    </Fragment>
  );
};

export default Home;
