LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'employeePunchTheClockMgmt',
} : {}), {
  i18nKey : 'employeePunchTheClockMgmt',
  $appendTo : true,
  cols : 3,
  url : '/SysEmployeePunchTheClock/P',
  columns : [
      {
        text : 'userName',
        name : 'userName',
        width : 120
      }, {
        text : 'cellphone',
        name : 'cellphone',
        width : 120
      }, {
        text : 'employeePunchTheClockTime',
        width : 145,
        formatter : function(rowData) {
          return showStandardTime(rowData.insertTime);
        }
      }, {
        text : 'employeePunchTheClockAddress',
        name : 'address',
        width : 120
      }, {
        text : 'appVersion_appKey',
        name : 'appKey',
        width : 120
      }, {
        text : 'appVersion_clientType',
        name : 'clientType',
        width : 120
      }, {
        text : 'appVersions',
        width : 120,
        formatter : function(rowData) {
          return rowData.versionX + '.' + rowData.versionY + '.' + rowData.versionZ;
        }
      }
  ],
  pageable : true,
  searchForm : [
      {
        plugin : 'textbox',
        options : {
          name : 'userName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'datepicker',
        options : {
          name : 'startDate'
        }
      }, {
        plugin : 'datepicker',
        options : {
          name : 'endDate'
        }
      }
  ]
}));
