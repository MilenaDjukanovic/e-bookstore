import {PublishingHouse} from "./publishing-house.model";
import {IBook} from "./book.model";

export interface IBaseBookManagementRequests {
  quantity: number;
  processedByUserId: number | null;
  reason: string;
  processed: boolean;
}

export interface ICreateBookManagementRequest extends IBaseBookManagementRequests {
  bookId: number;
  publishingHouseId: number;
}

export interface IBookManagementRequest extends IBaseBookManagementRequests {
  id: number
  book: IBook;
  publishingHouse: PublishingHouse;
}

export interface IUpdateBookManagementRequest extends IBaseBookManagementRequests {
  id: number,
  bookId: number;
  publishingHouseId: number;
}

export class CreateBookManagementRequest implements ICreateBookManagementRequest {
  constructor(public bookId: number,
              public quantity: number,
              public processedByUserId: number,
              public reason: string,
              public processed: boolean,
              public publishingHouseId: number) {
  }
}

export class BookManagementRequest implements IBookManagementRequest {
  constructor(public id: number,
              public book: IBook,
              public quantity: number,
              public processedByUserId: number,
              public reason: string,
              public processed: boolean,
              public publishingHouse: PublishingHouse) {
  }
}

export class UpdateBookManagementRequest implements IUpdateBookManagementRequest {
  constructor(public id: number,
              public bookId: number,
              public quantity: number,
              public processedByUserId: number | null,
              public reason: string,
              public processed: boolean,
              public publishingHouseId: number) {
  }
}
