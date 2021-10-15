<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>��Ŀ�� ���������� ǥ���ϱ�</title>
    
</head>
<body>



<%
	String x_pos = request.getParameter("x");
	String y_pos = request.getParameter("y");
	String str = request.getParameter("str");
%>



<div id="map" style="width:100%;height:350px;"></div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2e138c521b47422d9e2557bb2abb8e71"></script>
<script>

var x_p ='<%=x_pos%>';
var y_p ='<%=y_pos%>';
var str ='<%=str%>';

var mapContainer = document.getElementById('map'), // ������ ǥ���� div 
    mapOption = { 
        center: new kakao.maps.LatLng(x_p, y_p), // ������ �߽���ǥ
        level: 3 // ������ Ȯ�� ����
    };

var map = new kakao.maps.Map(mapContainer, mapOption);

// ��Ŀ�� ǥ�õ� ��ġ�Դϴ� 
var markerPosition  = new kakao.maps.LatLng(x_p, y_p); 

// ��Ŀ�� �����մϴ�
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// ��Ŀ�� ���� ���� ǥ�õǵ��� �����մϴ�
marker.setMap(map);

var iwContent = '<div style="padding:5px;">'+str+'<br><a href="https://map.kakao.com/link/map/'+str+','+x_p+','+y_p+'" style="color:blue" target="_blank">ū��������</a> <a href="https://map.kakao.com/link/to/'+str+','+x_p+','+y_p+'" style="color:blue" target="_blank">��ã��</a></div>', // ���������쿡 ǥ��� �������� HTML ���ڿ��̳� document element�� �����մϴ�
iwPosition = new kakao.maps.LatLng(x_p, y_p); //���������� ǥ�� ��ġ�Դϴ�


// ���������츦 �����մϴ�
var infowindow = new kakao.maps.InfoWindow({
    position : iwPosition, 
    content : iwContent 
});
  
// ��Ŀ ���� ���������츦 ǥ���մϴ�. �ι�° �Ķ������ marker�� �־����� ������ ���� ���� ǥ�õ˴ϴ�
infowindow.open(map, marker); 
</script>
</body>
</html>
