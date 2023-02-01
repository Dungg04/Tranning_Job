import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReportService } from '../services/report.service';

@Component({
  selector: 'app-confirm-report',
  templateUrl: './confirm-report.component.html',
  styleUrls: ['./confirm-report.component.css']
})
export class ConfirmReportComponent implements OnInit{

  constructor(private service: ReportService, private roter: Router, private route: ActivatedRoute){}

  ngOnInit(): void {
  }


  getReport() {
    this.route.params.subscribe( data => {
      this.service.getReport(data['receiptID']).subscribe(res => {
        const file = new Blob([res.body], { type: 'application/pdf' });
        const fileURL = window.URL.createObjectURL(file);
        const link = document.createElement('a');
          document.body.appendChild(link);
          link.download = fileURL;
          link.setAttribute('style', 'display: none');
          const name = 'ten_bao_cao.pdf';
          link.setAttribute('download', name);
          link.href = fileURL;
          link.click();
          this.roter.navigateByUrl("/reportReceipt")
        })
    })

  }
}
