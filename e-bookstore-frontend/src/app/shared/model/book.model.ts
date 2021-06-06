import {Author} from "./author.model";
import {Category} from "./category.model";
import {PublishingHouse} from "./publishing-house.model";

export interface IBaseBook {
  title: string;
  description: string;
  image: string;
  price: number;
  inStock: number;

}

export interface ICreateBook extends IBaseBook {
  authorId: number;
  categoryId: number;
}

export interface IBook extends IBaseBook {
  id: number;
  sold: number;
  publishingHouse: PublishingHouse;
  author: Author;
  category: Category;
}

export class CreateBook implements ICreateBook {
  constructor(public title: string,
              public description: string,
              public image: string,
              public price: number,
              public inStock: number,
              public authorId: number,
              public categoryId: number) {
  }
}

export class Book implements IBook {
  constructor(public id: number,
              public title: string,
              public description: string,
              public image: string,
              public price: number,
              public inStock: number,
              public sold: number,
              public author: Author,
              public category: Category,
              public publishingHouse: PublishingHouse) {
  }
}
