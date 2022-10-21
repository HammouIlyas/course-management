import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashbord',
  templateUrl: './dashbord.component.html',
  styleUrls: ['./dashbord.component.css'],
})
export class DashbordComponent implements OnInit {
  //employees: Observable<ApiResponse>;
  employees: string[] = ['', ''];
  constructor() {} //private router: Router //private employeeService: EmployeeService,

  ngOnInit() {}
}
