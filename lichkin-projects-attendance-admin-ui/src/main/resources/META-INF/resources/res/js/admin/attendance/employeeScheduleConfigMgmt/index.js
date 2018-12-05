LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'employeeScheduleConfigMgmt',
} : {}), {
  i18nKey : 'employeeScheduleConfigMgmt',
  $appendTo : true,
  cols : 2.5,
  url : '/SysEmployeeScheduleConfig/S',
  multiSelect : true,
  columns : [
      {
        text : 'userName',
        width : 160,
        name : 'userName'
      }, {
        text : 'scheduleType',
        width : 100,
        formatter : function(rowData, $grid, gridOptions) {
          return {
            plugin : 'droplist',
            options : {
              name : 'scheduleType',
              param : {
                categoryCode : 'SCHEDULE_TYPE'
              },
              value : rowData.scheduleType,
              onChange : function($droplist, pluginValues, pluginValue, currentValue) {
                if (currentValue == '') {
                  return;
                }
                LK.UI.openDialog({
                  size : {
                    cols : 1,
                    rows : 8
                  },
                  title : 'set',
                  icon : 'set',
                  mask : true,
                  buttons : [
                    {
                      text : 'save',
                      icon : 'save',
                      cls : 'warning',
                      click : function($button, $dialog, $contentBar) {
                        var $form = $contentBar.find('form');
                        if ($form.LKValidate()) {
                          var employeeId = $form.find('input[name=employeeId]').val();
                          var scheduleId = $form.find('input[name=scheduleId]').val();
                          var employeeSchedule = [];
                          $form.find('input[name=employeeSchedule]').each(function() {
                            employeeSchedule.push($(this).val());
                          });

                          LK.ajax({
                            url : '/SysEmployeeScheduleConfig/I',
                            data : {
                              employeeId : employeeId,
                              scheduleId : scheduleId,
                              scheduleInfo : employeeSchedule.join(LK.SPLITOR)
                            },
                            showSuccess : true,
                            success : function() {
                              $grid.LKLoad({
                                param : LK.UI._datagrid.getParam($grid, gridOptions)
                              });
                              $dialog.LKCloseDialog();
                            }
                          });
                        }
                      }
                    }
                  ],
                  onAfterCreate : function($dialog, $contentBar) {
                    var formFields = [];
                    formFields.push({
                      plugin : 'droplist',
                      options : {
                        key : 'employeeScheduleConfigMgmt.grid.columns.scheduleType',
                        name : 'scheduleId',
                        validator : true,
                        url : '/SysCompScheduleConfig/LD',
                        param : {
                          scheduleType : currentValue
                        },
                        linkages : [
                          'employeeSchedule'
                        ]
                      }
                    });

                    for (var i = 1; i <= 7; i++) {
                      formFields.push({
                        plugin : 'droplist',
                        options : {
                          key : 'employeeScheduleConfigMgmt.grid.columns.weekName',
                          keyTextReplaces : [
                            {
                              regex : 'N',
                              replacement : $.LKGetI18N('employeeScheduleConfigMgmt.grid.columns.weekDayName.' + i)
                            }
                          ],
                          name : 'employeeSchedule',
                          validator : true,
                          url : '/SysCompScheduleConfig/LD01',
                          lazy : true,
                          selectFirst : true,
                          onLinkaged : function($plugin, linkage) {
                            switch (linkage.linkageName) {
                              case 'scheduleId':
                                if (linkage.linkageValue == '') {
                                  $plugin.LKClearDatas();
                                } else {
                                  $plugin.LKLoad({
                                    param : {
                                      scheduleId : linkage.linkageValue
                                    }
                                  }, linkage);
                                }
                                break;
                            }
                          }
                        }
                      });
                    }

                    formFields.push({
                      plugin : 'hidden',
                      options : {
                        name : 'employeeId',
                        value : $droplist.data('gridRowData').id
                      }
                    });

                    var formOptions = $.extend({}, {
                      plugins : formFields
                    }, {
                      $appendTo : $contentBar
                    });
                    LK.UI.form(formOptions);
                  },
                  onAfterClose : function() {
                    $droplist.LKInvokeSetValues(pluginValue, false, true);
                  }
                });
              }
            }
          };
        }
      }, {
        text : 'scheduleName',
        width : 160,
        name : 'scheduleName'
      }, {
        text : 'remarks',
        width : null,
        name : 'remarks'
      }
  ],
  pageable : false,
  tools : [
      {
        singleCheck : true,
        icon : 'view',
        text : 'view',
        click : function($button, $datagrid, $selecteds, selectedDatas, value) {
          if (!selectedDatas.scheduleName) {
            LK.alert('employeeScheduleConfigMgmt.grid.noScheduleName');
            return;
          }
          LK.UI.openDialog({
            size : {
              cols : 1,
              rows : 9
            },
            title : 'view',
            icon : 'view',
            mask : true,
            buttons : [
              {
                text : 'cancel',
                icon : 'cancel',
                cls : 'danger',
                click : function($button, $dialog, $contentBar) {
                  $dialog.LKCloseDialog();
                }
              }
            ],
            onAfterCreate : function($dialog, $contentBar) {
              LK.ajax({
                url : '/SysEmployeeScheduleConfig/S01',
                data : {
                  id : value
                },
                success : function(data) {
                  var formFields = [];
                  formFields.push({
                    plugin : 'textbox',
                    options : {
                      key : 'employeeScheduleConfigMgmt.grid.columns.userName',
                      name : 'userName',
                      value : data.userName,
                      readonly : true
                    }
                  }, {
                    plugin : 'textbox',
                    options : {
                      key : 'employeeScheduleConfigMgmt.grid.columns.scheduleName',
                      name : 'scheduleName',
                      value : data.scheduleName,
                      readonly : true
                    }
                  });
                  if (data.scheduleTime != null) {
                    for (var i = 0; i < data.scheduleTime.length; i++) {
                      formFields.push({
                        plugin : 'textbox',
                        options : {
                          key : 'employeeScheduleConfigMgmt.grid.columns.weekName',
                          keyTextReplaces : [
                            {
                              regex : 'N',
                              replacement : $.LKGetI18N('employeeScheduleConfigMgmt.grid.columns.weekDayName.' + (i + 1))
                            }
                          ],
                          name : 'scheduleTime',
                          value : data.scheduleTime[i],
                          readonly : true
                        }
                      });
                    }
                  }

                  var formOptions = $.extend({}, {
                    plugins : formFields
                  }, {
                    $appendTo : $contentBar
                  });
                  LK.UI.form(formOptions);
                }
              });
            }
          });
        }
      }, {
        singleCheck : false,
        icon : 'remove',
        text : 'remove',
        click : function($button, $datagrid, $selecteds, selectedDatas, value) {
          var ids = [];
          for (var i = 0; i < selectedDatas.length; i++) {
            if (!selectedDatas[i].scheduleName) {
              LK.alert('employeeScheduleConfigMgmt.grid.noScheduleName');
              return;
            }
            ids.push(selectedDatas[i].employeeAttendanceId);
          }
          LK.confirm('employeeScheduleConfigMgmt.grid.confirmResetAttendance', function() {
            LK.ajax({
              url : '/SysEmployeeScheduleConfig/D',
              data : {
                id : ids.join(LK.SPLITOR)
              },
              showSuccess : true,
              success : function() {
                $datagrid.LKLoad();
              }
            });
          });
        }
      }, {
        singleCheck : false,
        icon : 'reset',
        text : 'resetEmployeeAttendanceBtn',
        click : function($button, $plugin, $selecteds, selectedDatas) {
          var ids = [];
          for (var i = 0; i < selectedDatas.length; i++) {
            if (!selectedDatas[i].scheduleName) {
              LK.alert('employeeScheduleConfigMgmt.grid.noScheduleName');
              return;
            }
            ids.push(selectedDatas[i].id);
          }
          LK.ajax({
            url : '/SysEmployeeAttendance/S',
            data : {
              id : ids.join(LK.SPLITOR)
            },
            showSuccess : true,
            success : function() {
              $plugin.LKLoad();
            }
          });
        }
      }
  ],
  searchForm : [
      {
        plugin : 'textbox',
        options : {
          name : 'userName',
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
