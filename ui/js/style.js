
var currentpos=1;
var timer;
var bcolor=document.getElementById('bcolor');
var txtcolor=document.getElementById('txtcolor');
var fonttype=document.getElementById('fonttype');


function setCookies(cookieName,cookieValue, expirehours)
{
  var today = new Date();
  var expire = new Date();
  expire.setTime(today.getTime() + 3600000 * 356 * 24);
  document.cookie = cookieName+'='+escape(cookieValue)+ ';expires='+expire.toGMTString()+'; path=/';
}
function ReadCookies(cookieName)
{
	var theCookie=''+document.cookie;
	var ind=theCookie.indexOf(cookieName);
	if (ind==-1 || cookieName=='') return ''; 
	var ind1=theCookie.indexOf(';',ind);
	if (ind1==-1) ind1=theCookie.length;
	return unescape(theCookie.substring(ind+cookieName.length+1,ind1));
}
function saveSet(bcolor,txtcolor,fonttype)
{
	//bcolor=document.getElementById('bcolor');
	//txtcolor=document.getElementById('txtcolor');
	//fonttype=document.getElementById('fonttype');
	setCookies("bcolor",bcolor);
	setCookies("txtcolor",txtcolor);
	setCookies("fonttype",fonttype);
}
function loadSet()
{
	var tmpstr;
	tmpstr = ReadCookies("bcolor");
	bcolor.selectedIndex = 0;
	if (tmpstr != "")
	{
	    for (var i=0;i<bcolor.length;i++)
		{
			if (bcolor.options[i].value == tmpstr)
			{
			    
				bcolor.selectedIndex = i;
				break;
			}
		}
	}
	tmpstr = ReadCookies("txtcolor");
	txtcolor.selectedIndex = 0;
	if (tmpstr != "")
	{
		for (var i=0;i<txtcolor.length;i++)
		{
			if (txtcolor.options[i].value == tmpstr)
			{
				txtcolor.selectedIndex = i;
				break;
			}
		}
	}
	tmpstr = ReadCookies("fonttype");
	fonttype.selectedIndex = 0;
	if (tmpstr != "")
	{
		for (var i=0;i<fonttype.length;i++)
		{
			if (fonttype.options[i].value == tmpstr)
			{
				fonttype.selectedIndex = i;
				break;
			}
		}
	}
	

	document.getElementById('content').style.backgroundColor=bcolor.options[bcolor.selectedIndex].value;

	contentobj=document.getElementById('htmlContent');
	contentobj.style.fontSize=fonttype.options[fonttype.selectedIndex].value;
	contentobj.style.color=txtcolor.options[txtcolor.selectedIndex].value;
}


