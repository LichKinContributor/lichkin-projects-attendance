var compScheduleConfigMgmtStartCommonFields = [
    {
      plugin : 'textbox',
      options : {
        name : 'scheduleName',
        validator : true,
        maxlength : 20
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'remarks',
        maxlength : 512
      }
    }, {
      plugin : 'timepicker',
      options : {
        key : 'schedule_startTime',
        name : 'startTime0',
        validator : true
      }
    }, {
      plugin : 'timepicker',
      options : {
        key : 'schedule_endTime',
        name : 'endTime0',
        validator : true
      }
    }
];

var compScheduleConfigMgmtForm_NORMAL = [
  {
    plugin : 'hidden',
    options : {
      name : 'scheduleType',
      value : 'NORMAL'
    }
  }
];
var compScheduleConfigMgmtForm_DOUBLE = [
  {
    plugin : 'hidden',
    options : {
      name : 'scheduleType',
      value : 'DOUBLE'
    }
  }
];
var compScheduleConfigMgmtForm_THRIPLE = [
  {
    plugin : 'hidden',
    options : {
      name : 'scheduleType',
      value : 'THRIPLE'
    }
  }
];

$(compScheduleConfigMgmtStartCommonFields).each(function(i, obj) {
  compScheduleConfigMgmtForm_NORMAL.push(obj);
  compScheduleConfigMgmtForm_DOUBLE.push(obj);
  compScheduleConfigMgmtForm_THRIPLE.push(obj);
});

compScheduleConfigMgmtForm_DOUBLE.push({
  plugin : 'timepicker',
  options : {
    key : 'schedule_startTime',
    name : 'startTime1',
    validator : true
  }
}, {
  plugin : 'timepicker',
  options : {
    key : 'schedule_endTime',
    name : 'endTime1',
    validator : true
  }
});

compScheduleConfigMgmtForm_THRIPLE.push({
  plugin : 'timepicker',
  options : {
    key : 'schedule_startTime',
    name : 'startTime1',
    validator : true
  }
}, {
  plugin : 'timepicker',
  options : {
    key : 'schedule_endTime',
    name : 'endTime1',
    validator : true
  }
}, {
  plugin : 'timepicker',
  options : {
    key : 'schedule_startTime',
    name : 'startTime2',
    validator : true
  }
}, {
  plugin : 'timepicker',
  options : {
    key : 'schedule_endTime',
    name : 'endTime2',
    validator : true
  }
});

var compScheduleConfigMgmtEndCommonFields = [
    {
      plugin : 'numberspinner',
      options : {
        name : 'allowBeforeStartTimeMinutes',
        value : 60,
        min : 0,
        max : 60
      }
    }, {
      plugin : 'numberspinner',
      options : {
        name : 'allowAfterStartTimeMinutes',
        value : 60,
        min : 0,
        max : 60
      }
    }, {
      plugin : 'numberspinner',
      options : {
        name : 'allowBeforeEndTimeMinutes',
        value : 15,
        min : 0,
        max : 60
      }
    }, {
      plugin : 'numberspinner',
      options : {
        name : 'allowAfterEndTimeMinutes',
        value : 5,
        min : 0,
        max : 60
      }
    }
];

$(compScheduleConfigMgmtEndCommonFields).each(function(i, obj) {
  compScheduleConfigMgmtForm_NORMAL.push(obj);
  compScheduleConfigMgmtForm_DOUBLE.push(obj);
  compScheduleConfigMgmtForm_THRIPLE.push(obj);
});

var $datagrid = LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'compScheduleConfigMgmt',
} : {}), {
  i18nKey : 'compScheduleConfigMgmt',
  $appendTo : true,
  cols : 4,
  rows : 15,
  url : '/SysCompScheduleConfig/L',
  multiSelect : false,
  columns : [
      {
        text : 'scheduleName',
        name : 'scheduleName',
        width : 250
      }, {
        text : 'scheduleType',
        name : 'scheduleType',
        width : 80
      }, {
        text : 'schedule_startTime',
        name : 'startTime0',
        width : 80
      }, {
        text : 'schedule_endTime',
        name : 'endTime0',
        width : 80
      }, {
        text : 'schedule_startTime',
        name : 'startTime1',
        width : 80
      }, {
        text : 'schedule_endTime',
        name : 'endTime1',
        width : 80
      }, {
        text : 'schedule_startTime',
        name : 'startTime2',
        width : 80
      }, {
        text : 'schedule_endTime',
        name : 'endTime2',
        width : 80
      }, {
        text : 'remarks',
        name : 'remarks',
        width : null
      }
  ],
  pageable : false,
  toolsAdd : [
      {
        text : 'add_threeShifts_btn',
        saveUrl : '/SysCompScheduleConfig/I',
        dialog : {
          size : {
            cols : 2,
            rows : 6
          }
        },
        form : {
          plugins : compScheduleConfigMgmtForm_THRIPLE
        }
      }, {
        text : 'add_doubleShift_btn',
        saveUrl : '/SysCompScheduleConfig/I',
        dialog : {
          size : {
            cols : 2,
            rows : 5
          }
        },
        form : {
          plugins : compScheduleConfigMgmtForm_DOUBLE
        }
      }, {
        text : 'add_normal_btn',
        saveUrl : '/SysCompScheduleConfig/I',
        dialog : {
          size : {
            cols : 2,
            rows : 4
          }
        },
        form : {
          plugins : compScheduleConfigMgmtForm_NORMAL
        }
      }
  ],
  toolsEdit : {
    beforeOpenDialog : function(editJson, $button, $datagrid, $selecteds, selectedDatas, value) {
      var formSize;
      var editForm;

      switch (selectedDatas.scheduleTypeDictCode) {
        case 'NORMAL':
          formSize = {
            cols : 2,
            rows : 4
          };
          editForm = [].concat(compScheduleConfigMgmtForm_NORMAL);
          break;
        case 'DOUBLE':
          formSize = {
            cols : 2,
            rows : 5
          };
          editForm = [].concat(compScheduleConfigMgmtForm_DOUBLE);
          break;
        case 'THRIPLE':
          formSize = {
            cols : 2,
            rows : 6
          };
          editForm = [].concat(compScheduleConfigMgmtForm_THRIPLE);
          break;
      }

      return {
        saveUrl : '/SysCompScheduleConfig/U',
        dialog : {
          size : formSize
        },
        form : {
          url : '/SysCompScheduleConfig/O',
          plugins : editForm
        }
      }
    }
  },
  toolsRemove : {
    saveUrl : '/SysCompScheduleConfig/US'
  },
  searchForm : [
      {
        plugin : 'textbox',
        options : {
          name : 'scheduleName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'droplist',
        options : {
          name : 'scheduleType',
          param : {
            categoryCode : 'SCHEDULE_TYPE'
          }
        }
      }
  ]
}));
