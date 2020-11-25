import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import {TasksService} from '../../services/tasks/api/tasks.service';
import {Task} from '../../services/tasks/model/task';

import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatFormFieldControl } from '@angular/material/form-field';
import { MatSelect } from '@angular/material/select';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  @ViewChild('dialog', {static: false})
  public dialog: TemplateRef<any>;
  taskStatusOpts = [
    {
      value: 'inProgress',
      label: 'In progress'
    },
    {
      value: 'pending',
      label: 'Pending'
    },
    {
      value: 'finished',
      label: 'Finished'
    }
  ];

  tasks: Task[];

  newTaskData = {
    show: false,
    control: new FormControl('')
  };

  deleteTaskData = {
    index: null,
    dialog: null
  };
  
  constructor(private tasksService: TasksService, private matDialog: MatDialog, private _snackBar: MatSnackBar) {}

  ngOnInit(): void {
    this.getTasks();
  }

  public newTask() {
    this.newTaskData.show = true;
    this.newTaskData.control.setValue('');
  }

  public createTask() {
    const newTask = {
      status: 'pending',
      description: this.newTaskData.control.value
    }
    console.log('Creating task', newTask);
    this.tasksService.addTask(newTask).subscribe(
      success => {
        this.getTasks();
        this.newTaskData.show = false;
        this.newTaskData.control.setValue('');
        this.openSnackBar('Task created successfully','');
        console.log('Tarea creada con exito');
      }, error => {
        console.log('Fallo en creacion de tarea');
      })
  }

  public cancelNewTask() {
    this.newTaskData.show = false;
    this.newTaskData.control.setValue('');
  }

  public updateTask(idx) {
    const taskToUpdate = this.tasks[idx];
    if (taskToUpdate) {
      console.log('Updating task', taskToUpdate);
      this.tasksService.updateTask(taskToUpdate).subscribe(
        success => {
          this.getTasks();
          this.openSnackBar('Task with id ' + taskToUpdate.id + ' updated successfully','');
        }, error => {

        }
      );
    }
  }

  public deleteTask(idx) {
    this.deleteTaskData.index = idx;
    this.deleteTaskData.dialog = this.matDialog.open(this.dialog);
  }

  public deleteConfirm() {
    const taskToDelete = this.tasks[this.deleteTaskData.index];
    if (taskToDelete) {
      console.log('Deleting task', taskToDelete);
      this.tasksService.deleteTask(taskToDelete).subscribe(
        success => {
          this.getTasks();
          this.openSnackBar('Task with id ' + taskToDelete.id + ' removed successfully','');
        }, error => {

        }
      );
    }
    this.deleteTaskData.dialog.close();
    this.deleteTaskData.index = null;
    this.deleteTaskData.dialog = null;
  }

  public deleteCancel() {
    this.deleteTaskData.dialog.close();
    this.deleteTaskData.index = null;
    this.deleteTaskData.dialog = null;
  }

  private getTasks() {
    this.tasksService.listTasks().subscribe((data: Task[]) => {
      console.log(data);
      this.tasks = data;
    });
  }

  public openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, {
      duration: 2000,
    });
  }

}
