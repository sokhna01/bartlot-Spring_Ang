import { Injectable } from '@angular/core';
import * as Highcharts from 'highcharts';

@Injectable({
  providedIn: 'root'
})



export class CreateHighChartService {

  chartPuissance: any;
  chartPresence: any;
  chartQualite: any;

  plotChart(chartType: string,
    listPuissancePr: Array<any> = [],
    listPresencePr: Array<any> = [],
    listPuissanceRe: Array<any> = [],
    listPresenceRe: Array<any> = [],
    listQualitePr: Array<any> = [],
    listQualiteRe: Array<any> = []) {

    let optionsPuissance: Highcharts.Options = {
      chart: {
        type: chartType,
        backgroundColor: '#000000',
        zoomType: 'xy',
        // labels: {
        //   style: {
        //     color: 'white'
        //   }
        // }
      },
      title: {
        text: 'Puissance',
        style: {
          color: 'white'
        }
      },
      xAxis: {
        categories: [],
        labels: { style: { color: 'white' } },
        crosshair: true
      },
      yAxis: {
        min: 0,
        title: {
          text: 'Puissance',
          style: {
            color: 'white'
          }
        },
        labels: { style: { color: 'white' } },
      },
      tooltip: {
        formatter: function () {
          return ' ' +
            'Compteur: ' + this.series.name + '<br />' +
            'Puissance: ' + this.point.y + '<br />';
        }
      },
      plotOptions: {
        series: {
          turboThreshold: 0
          // pointPadding: 0.2,
          // borderWidth: 0
        }
      }
    };
    let optionsPresence: Highcharts.Options = {
      chart: {
        type: chartType,
        backgroundColor: '#000000',
        zoomType: 'xy',
        // labels: {
        //   style: {
        //     color: 'white'
        //   }
        // }
      },
      title: {
        text: 'Présence',
        style: {
          color: 'white'
        }
      },
      xAxis: {
        categories: [],
        labels: { style: { color: 'white' } },
        crosshair: true
      },
      yAxis: {
        min: 0,
        labels: { style: { color: 'white' } },
        title: {
          text: 'Présence',
          style: {
            color: 'white'
          }
        }
      },
      tooltip: {
        formatter: function () {
          // Récupérer la date pour le point survolé
          return ' ' +
            'Compteur: ' + this.series.name + '<br />' +
            'Presence: ' + this.point.y + '<br />' /* +
            'Date: ' + Highcharts.dateFormat('%b %d, %Y, %I:%M:%S %p', this.point.datetime) */;
        }
      },
      plotOptions: {
        series: {
          turboThreshold: 0,
          // pointPadding: 0.2,
          // borderWidth: 0
        }
      }
    };
    let optionsQualite: Highcharts.Options = {
      chart: {
        type: chartType,
        backgroundColor: '#000000',
        zoomType: 'xy',
      },
      title: {
        text: 'Qualité',
        style: {
          color: 'white'
        }
      },
      xAxis: {
        categories: [],
        labels: { style: { color: 'white' } },
        crosshair: true
      },
      yAxis: {
        min: 0,
        labels: { style: { color: 'white' } },
        title: {
          text: 'Qualité',
          style: {
            color: 'white'
          }
        }
      },
      tooltip: {
        formatter: function () {
          return ' ' +
            'Compteur: ' + this.series.name + '<br />' +
            'Puissance: ' + this.point.y + '<br />';
        }
      },
      plotOptions: {
        series: {
          turboThreshold: 0
          // pointPadding: 0.2,
          // borderWidth: 0
        }
      }
    };

    this.chartPuissance = new Highcharts.Chart('main-head', optionsPuissance);
    this.chartPuissance.addSeries({
      name: 'Puissance Principal',
      data: listPuissancePr,
      color: '#B61E47',
      marker: {
        symbol: 'circle'
      }
    }, false);
    this.chartPuissance.addSeries({
      name: 'Puissance Redondant',
      data: listPuissanceRe,
      color: '#EDF34B',
      marker: {
        symbol: 'square'
      }
    }, false);
    this.chartPuissance.redraw();

    this.chartPresence = new Highcharts.Chart('main-head1', optionsPresence);
    this.chartPresence.addSeries({
      name: 'Presence Principal',
      data: listPresencePr,
      color: '#B61E47',
      marker: {
        symbol: 'circle'
      }
    }, false);
    this.chartPresence.addSeries({
      name: 'Presence Redondant',
      data: listPresenceRe,
      color: '#EDF34B',
      marker: {
        symbol: 'square'
      }
    }, false);
    this.chartPresence.redraw();

    this.chartQualite = new Highcharts.Chart('main-head2', optionsQualite);
    this.chartQualite.addSeries({
      name: 'Qualité Principal',
      data: listQualitePr,
      color: '#B61E47',
      marker: {
        symbol: 'circle'
      },
      zones: [{
        value: 1.01,
        color: '#90ed7d'
      },
      {
        value: 2.01,
        color: '#EDF34B'
      },
      {
        color: '#B61E47'
      }]
    }, false);
    this.chartQualite.addSeries({
      name: 'Qualité Redondant',
      data: listQualiteRe,
      color: '#EDF34B',
      marker: {
        symbol: 'square'
      },
      zones: [{
        value: 1.01,
        color: '#90ed7d'
      },
      {
        value: 2.01,
        color: '#EDF34B'
      },
      {
        color: '#B61E47'
      }]
    }, false);
    this.chartQualite.addSeries({
      name: 'Légende',
      type: 'line',
      data: [{ x: 0, y: 0 }, { x: 0, y: 1 }, { x: 0, y: 2 }, { x: 0, y: 3 }],
      lineWidth: 13,
      zones: [{
        name: '',
        value: 1,
        color: '#90ed7d'
      },
      {
        value: 2,
        color: '#EDF34B'
      },
      {
        color: '#B61E47'
      }]
    }, false);
    this.chartQualite.redraw();

  }
}

