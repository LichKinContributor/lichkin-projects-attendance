LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'employeeAttendanceMgmt',
} : {}), {
  i18nKey : 'employeeAttendanceMgmt',
  $appendTo : true,
  cols : 4,
  url : '/SysEmployeeAttendance/P',
  multiSelect : false,
  columns : [
      {
        text : 'userName',
        name : 'userName',
        width : 160
      }, {
        text : 'scheduleDate',
        name : 'workDate',
        width : 90
      }, {
        text : 'dayOff',
        width : 80,
        formatter : function(rowData) {
          return LK.UI.formatter.bool(rowData.dayOff);
        }
      }, {
        text : 'takeWorkingDayOff',
        width : 80,
        formatter : function(rowData) {
          return LK.UI.formatter.bool(rowData.takeWorkingDayOff);
        }
      }, {
        text : 'askForLeave',
        width : 80,
        formatter : function(rowData) {
          return LK.UI.formatter.bool(rowData.askForLeave);
        }
      }, {
        text : 'absenteeism',
        width : 80,
        formatter : function(rowData) {
          return LK.UI.formatter.bool(rowData.absenteeism);
        }
      }, {
        text : 'beLate',
        width : 80,
        formatter : function(rowData) {
          return LK.UI.formatter.bool(rowData.beLate);
        }
      }, {
        text : 'leaveEarly',
        width : 80,
        formatter : function(rowData) {
          return LK.UI.formatter.bool(rowData.leaveEarly);
        }
      }, {
        text : 'attendance_startTime',
        name : 'startTime',
        width : 140
      }, {
        text : 'attendance_endTime',
        name : 'endTime',
        width : 140
      }, {
        text : 'firstPunchTime',
        name : 'firstPunchTime',
        width : 140
      }, {
        text : 'lastPunchTime',
        name : 'lastPunchTime',
        width : 140
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
          name : 'startDate',
          maxDate : nextMonthDay()
        }
      }, {
        plugin : 'datepicker',
        options : {
          name : 'endDate',
          maxDate : nextMonthDay()
        }
      }
  ]
}));
