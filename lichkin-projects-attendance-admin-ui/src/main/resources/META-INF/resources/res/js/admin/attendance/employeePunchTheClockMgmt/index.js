LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'employeePunchTheClockMgmt',
} : {}), {
  i18nKey : 'employeePunchTheClockMgmt',
  $appendTo : true,
  cols : 4,
  url : '/SysEmployeePunchTheClock/P',
  columns : [
      {
        text : 'userName',
        name : 'userName',
        width : 80
      }, {
        text : 'cellphone',
        name : 'cellphone',
        width : 100
      }, {
        text : 'insertTime',
        width : 140,
        formatter : function(rowData) {
          return showStandardTime(rowData.insertTime);
        }
      }, {
        text : 'address',
        name : 'address',
        width : null
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
