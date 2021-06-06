import {AfterViewInit, Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit, AfterViewInit {

  public tableDataSource = new MatTableDataSource(this.data);

  @ViewChild(MatPaginator, {static: false}) paginator!: MatPaginator;
  @ViewChild(MatSort, {static: true}) matSort!: MatSort;

  @Input() public tableColumnDefinition: Array<any> = new Array<any>();
  @Input() public defaultActions = new Array<any>();
  @Input() public numberOfElements!: number;
  @Input() public numberOfPages!: number;
  @Input() public pageSize = 4;

  @Output() pageChangedEvent: EventEmitter<any> = new EventEmitter<any>();
  @Output() actionClickedEvent: EventEmitter<any> = new EventEmitter<any>();

  public columnsForDisplay: Array<any> = new Array<any>();

  constructor() {
  }

  @Input() set data(data: any) {
    this.tableDataSource = new MatTableDataSource(data);
  }

  ngOnInit(): void {
    this.initializeColumnsForDisplay();
  }

  ngAfterViewInit() {
    this.tableDataSource.paginator = this.paginator;
  }

  public initializeColumnsForDisplay(): void {
    const columnsToDisplay = this.tableColumnDefinition.map(columnDefinition => {
      return columnDefinition['property'];
    });

    if (this.defaultActions.length != 0) {
      columnsToDisplay.push('actions');
    }

    this.columnsForDisplay =  columnsToDisplay;
  }

  public getDisplayValueForProperty(element: any, property: string): string {
    const propertyArray = property.split(".");
    let placeholder = element;

    for (const prop of propertyArray) {
      if (!placeholder) {
        return "";
      }
      placeholder = placeholder[prop];
    }

    return placeholder;
  }

  public onPageChanged(event: any): void {
    this.pageChangedEvent.emit(event);
  }

  actionClicked(element: any, index: any, actionName: any) {
    const elementToEmit = {
      element: element,
      actionName: actionName
    }

    this.actionClickedEvent.emit(elementToEmit);
  }
}
