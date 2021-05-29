export interface IBaseCategory {
    name: string;
}

export interface ICreateCategory extends IBaseCategory {}

export interface ICategory extends IBaseCategory {
    id: number;
}

export class CreateCategory implements ICreateCategory {
  constructor(public name: string) {
  }
}

export class Category implements ICategory {
  constructor(public id: number,
              public name: string) {
  }
}
