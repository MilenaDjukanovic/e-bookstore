import { environment } from "../../../environments/environment";

const ORDERS_API_PRIVATE = environment.bookstore.api.base + environment.bookstore.api.private.base;

export const OrdersApi = {
  public: {
    purchase: ORDERS_API_PRIVATE + environment.bookstore.api.private.orders.order
  }
}
