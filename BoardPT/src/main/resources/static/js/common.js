//TimeStamp -> Date formatter
function dateFormatter(date) {
	// yyyy-mm-dd hh:mm:ss.s
	var dateFormatt	= new Date( date );
	
	var year	= dateFormatt.getFullYear();
	
	var month	= 0;
	if ( dateFormatt.getUTCMonth() < 9 ){
		month	= '0'+ ( dateFormatt.getUTCMonth() + 1 ).toString();
	} else {
		month	= dateFormatt.getUTCMonth()+ 1;
	}
	var day		= dateFormatt.getUTCDate();

	var hour	= 0;
	if ( dateFormatt.getHours() < 10 ){
		hour	= '0' + (dateFormatt.getHours()).toString();
	} else {
		hour	= dateFormatt.getHours();
	}

	var minute	= 0;
	if ( dateFormatt.getMinutes() < 10 ){
		minute	= '0' + ( dateFormatt.getMinutes()).toString();
	} else {
		minute	= dateFormatt.getMinutes();
	}

	var seconds	= 0;
	if ( dateFormatt.getSeconds() < 10 ){
		seconds	= '0' + (dateFormatt.getSeconds()).toString();
	} else {
		seconds	= dateFormatt.getSeconds();
	}

	var milliseconds = dateFormatt.getMilliseconds();
	var fullDateFormatt;
	fullDateFormatt = year +'-'+month+'-'+day+' '+hour+':'+minute+':'+seconds; // +'.'+milliseconds;
	console.log ('DateFormat : ' + fullDateFormatt);
	return fullDateFormatt;
}


function ajaxAction(url, param, successFn){
	$.ajax({
		url		: url,
		data	: param,
		success	: function() {
			successFn;
		}
	});
}




