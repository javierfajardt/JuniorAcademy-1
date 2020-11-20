import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Task } from './task';

@Injectable({
  providedIn: 'root'
})

export class TaskService {

  constructor(private http: HttpClient) { }
  private tasksUrl = 'http://localhost:8080/tasks';
  private allTasksUrl = 'http://localhost:8080/allTasks';
  private taskByIdUrl = 'http://localhost:8080/taskById';

  getTasks(): Observable<Task[]>{
    return this.http.get<Task[]>(`${this.tasksUrl}`);
  }

  addTask(task: Task): Observable<Task>{
    return this.http.post<Task>(`${this.tasksUrl}`, task);
  }

  updateTask(task: Task): Observable<Task>{
    return this.http.put<Task>(`${this.tasksUrl}`, task);
  }

  deleteTask(task: Task): void{
    const header: HttpHeaders = new HttpHeaders()
      .append('Content-Type', 'application/json; charset=UTF-8')
      .append('Authorization', 'Bearer ' + sessionStorage.getItem('accessToken'));
    const httpOptions = {
      headers: header,
      body: { Task: task }
    };
    this.http.delete(`${this.tasksUrl}`, httpOptions);
  }

  getTaskById(id: number): Observable<Task> {
    const params1 = new HttpParams().set('id', id.toString());
    return this.http.get<Task>(`${this.taskByIdUrl}`, {params: params1});
  }

  deleteTaskById(id: number): void {
    const params1 = new HttpParams().set('id', id.toString());
    this.http.delete(`${this.taskByIdUrl}`, {params: params1});
  }

  countTasks(): Observable<number>{
    return this.http.get<number>(`${this.allTasksUrl}`);
  }

  deleteTasks(): void{
    this.http.delete(`${this.allTasksUrl}`);
  }
}
