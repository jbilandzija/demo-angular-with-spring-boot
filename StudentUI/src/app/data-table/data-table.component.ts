import {Component, OnInit} from '@angular/core';
import {StudentService} from '../service/student.service';
import {Observable} from 'rxjs';
import {DataSource} from '@angular/cdk/collections';
import {Student} from '../models/student.model';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent implements OnInit {

  dataSource = new StudentDataSource(this.studentService);
  displayedColumns = ['name'];

  constructor(private studentService: StudentService) {
  }

  ngOnInit() {
  }

}

export class StudentDataSource extends DataSource<any> {
  constructor(private studentService: StudentService) {
    super();
  }

  connect(): Observable<Student[]> {
    return this.studentService.getStudents();
  }

  disconnect() {
  }
}
