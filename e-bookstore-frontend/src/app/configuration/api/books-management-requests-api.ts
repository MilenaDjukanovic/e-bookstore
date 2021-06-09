import { environment } from "../../../environments/environment";

const BOOKS_MANAGEMENT_REQUESTS_API_PRIVATE = environment.bookstore.api.base + environment.bookstore.api.private.base;

export const BooksManagementRequestsApi = {
  public: {
    findAll: BOOKS_MANAGEMENT_REQUESTS_API_PRIVATE + environment.bookstore.api.private.bookManagementRequests.findAll
  },
  private: {
    approve: BOOKS_MANAGEMENT_REQUESTS_API_PRIVATE + environment.bookstore.api.private.bookManagementRequests.approve,
    delete: BOOKS_MANAGEMENT_REQUESTS_API_PRIVATE + environment.bookstore.api.private.bookManagementRequests.delete,
    create: BOOKS_MANAGEMENT_REQUESTS_API_PRIVATE + environment.bookstore.api.private.bookManagementRequests.create,
    findAllPending: BOOKS_MANAGEMENT_REQUESTS_API_PRIVATE + environment.bookstore.api.private.bookManagementRequests.findAllPending,
  }
}
