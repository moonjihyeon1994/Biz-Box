var kakaoMapObj = {};

var BzMapManager = {
    init() {
        // --맵 생성--------------------------------------------------------------------------------------
        kakaoMapObj = new kakao.maps.Map(container, options) // 맵 생성
        kakao.maps.event.addListener(this.map, 'bounds_changed', () => {
          var bounds = Map.getBounds() // 지도 영역정보
          var swLatlng = bounds.getSouthWest() // 영역정보의 남서쪽 정보
          var neLatlng = bounds.getNorthEast() // 영역정보의 북동쪽 정보
          this.message =
            '영역좌표 :' + swLatlng.toString() + '/' + neLatlng.toString() + ''
        })
    },

    // 마커 생성
    createMarker() {

    }

    getMap() {
        return kakaoMapObj;
    },

    panTo(posX, posY) {
    var moveLatLon = new kakao.maps.LatLng(posX, posY) // 이동할 위도 경도 위치를 생성합니다
      this.getMap().panTo(moveLatLon)
    }

    serch(name, marker, infowindow, callback) {
        
      let Ifchange = this.ifchanege
      let Name = this.name
      let Map = this.getMap()
      let Marker = this.markerx
      let InfoWindow = this.infowindow
      this.geocoder.addressSearch(this.name, function (result, status) {
        callback(result, status)
      })
    }
}