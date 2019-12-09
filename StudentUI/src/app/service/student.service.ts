import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Student} from '../models/student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private serviceUrl = 'http://localhost:8082/v1/student';

  constructor(private http: HttpClient) {  }

  getStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.serviceUrl);
  }
}
