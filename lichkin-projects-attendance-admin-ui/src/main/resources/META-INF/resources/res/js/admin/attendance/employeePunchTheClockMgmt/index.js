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
  tools : [
    {
      singleCheck : true,
      icon : 'view',
      text : 'view',
      click : function($button, $datagrid, $selecteds, selectedDatas, value) {
        LK.UI.openDialog($.extend({}, {
          size : {
            cols : 4,
            rows : 14
          }
        }, {
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
            LK.UI.form({
              $appendTo : $contentBar,
              plugins : [
                {
                  plugin : 'map',
                  options : {
                    key : 'employeePunchTheClockMgmt.grid.columns.coverage',
                    name : 'coverage',
                    value : {
                      longitude : selectedDatas.longitude,
                      latitude : selectedDatas.latitude,
                      markerLabel : selectedDatas.address
                    }
                  }
                }
              ]
            });
          }
        }));
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
