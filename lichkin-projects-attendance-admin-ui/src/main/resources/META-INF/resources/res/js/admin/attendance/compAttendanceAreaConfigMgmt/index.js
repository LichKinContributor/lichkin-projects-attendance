var $compAttendanceAreaConfigMgmtForm = LK.UI.form($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'compAttendanceAreaConfigMgmt',
} : {}), {
  i18nKey : 'compAttendanceAreaConfigMgmt.',
  $appendTo : true,
  createSubPluginNoDatas : true,
  url : '/SysCompAttendanceAreaConfig/S',
  plugins : [
    {
      plugin : 'map',
      options : {
        name : 'coverage',
        overlay : 'Circle',
        validator : true,
      }
    }
  ]
}));

var _admin_attendance_compAttendanceAreaConfigMgmt_index_dynamicButtons = [
    {
      text : 'save',
      icon : 'save',
      cls : 'warning',
      click : function($dialogButton, $dialog) {
        var $form = $dialog.find('form');
        if ($form.LKValidate()) {
          LK.ajax({
            url : '/SysCompAttendanceAreaConfig/S01',
            data : $form.LKFormGetData(),
            success : function(responseData) {
              LK.alert('compAttendanceAreaConfigMgmt.set ok');
            }
          });
        }
      }
    }, {
      text : 'cancel',
      icon : 'cancel',
      cls : 'danger',
      click : function($dialogButton, $dialog) {
        $dialog.LKCloseDialog();
      }
    }
];
