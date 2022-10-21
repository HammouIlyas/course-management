import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tdashbord',
  templateUrl: './tdashbord.component.html',
  styleUrls: ['./tdashbord.component.css'],
})
export class TdashbordComponent implements OnInit {
  employees: string[] = ['', ''];
  constructor() {} //private router: Router //private employeeService: EmployeeService,

  ngOnInit(): void {}
}
