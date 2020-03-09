<%--
  Created by IntelliJ IDEA.
  User: DANG VAN DAT
  Date: 8/31/2019
  Time: 12:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<c:url var = 'buildingAPI' value="/api-building" />
<c:url var="buildingURL" value="/admin-building" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit Page</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Trang chủ</a>
                </li>
                <li>
                    <i class="ace-icon fa fa-list list-icon"></i>
                    Danh Sách Tòa Nhà
                </li>
                <li>
                    Edit Page
                </li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <form id="formEdit">
            <div class="row" >
                <div class="col-xs-12">
                    <div class="widget-box table-filter">
                        <div class="widget-header">
                            <h4 class="widget-title">
                                Edit & ADD
                            </h4>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collopse"><i class="ace-icon fa fa-chevron-up"></i></a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Tên Tòa Nhà: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="name" value="${model.name}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Diện Tích Sàn: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="buildingArea" value="${model.buildingArea}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Mô Tả Diện Tích: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <textarea cols="134" rows="5" name=""></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Phường: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="ward" value="${model.ward}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Đường: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="street" value="${model.street}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Quận: </b></label>
                                        </div>
                                        <div class="col-sm-2">
                                            <select class="form-control" name="district">
                                                <option value="">---Chọn Quận---</option>
                                                <c:forEach var="item" items="${districts}">
                                                    <option value="${item.key}" ${item.key == model.district ? 'selected' : ''}>${item.value}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Hướng: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="direction" value="${model.direction}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Hạng: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="levelBuilding" value="${model.levelBuilding}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Số Tầng Hầm: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="numberOfBasement" value="${model.numberOfBasement}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Diện Tích Thuê: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="rentArea" value="${model.rentArea}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Giá Thuê: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control input-sm" name="costRent" value="${model.costRent}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Mô Tả Giá: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="cosDescription" value="${model.cosDescription}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Tên Quản Lý: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="managerName" value="${model.managerName}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Số Điện Thoại Quản Lý: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="number" class="form-control input-sm" name="managerPhone" value="${model.managerPhone}"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Kết Cấu: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="" value=""/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Phí Dịch Vụ: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="serviceCost" value="${model.serviceCost}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Phí Mô Tô: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="motobikeCost" value="${model.motobikeCost}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Phí Ô Tô: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="carCost" value="${model.carCost}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Phí Ngoài Giờ: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="overtimeCost" value="${model.overtimeCost}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Tiền Điện: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="electtriCost" value="${model.electtriCost}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Đặt Cọc: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="" value=""/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Thanh Toán: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="payment" value="${model.payment}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Thời Hạn Thuê: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="timeRent" value="${model.timeRent}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Thời Gian Trang Trí: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="timeDecorater" value="${model.timeDecorater}"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Phí Môi Giới: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control input-sm" name="deposit" value="${model.deposit}"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Hình Ảnh: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <input type="file" class="form-control input-sm" name="" value=""/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3">
                                            <label><b>Loại Tòa Nhà: </b></label>
                                        </div>
                                        <div class="col-sm-9">
                                            <c:forEach var="item" items="${buildingTypes}">
                                                <label class="checkbox-inline"><input type="checkbox" value="${item.key}" ${fn:contains(fn:join(model.buildingTypes , ','), item.key) ? 'checked' :''} name="buildingTypes"><b>${item.value}</b></label>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="id" value="${model.id}" id="buildingId" />
            </form>
            <div class="row text-center btn-addsp">
                <br>
                <c:if test = "${empty model.id}">
                <button type="button" class="btn btn-success" id="btnAddOrUpdateBuilding">Thêm Mới</button>
                <button type="button" class="btn btn-danger">Hủy Bỏ</button>
                </c:if>
                <c:if test = "${not empty model.id}">
                    <button type="button" class="btn btn-success" id="btnAddOrUpdateBuilding">Cập Nhật Tòa Nhà</button>
                    <button type="button" class="btn btn-danger">Hủy Bỏ</button>
                </c:if>
            </div>
        </div>
    </div>
</div>

<script>
    $("#btnAddOrUpdateBuilding").click(function(){
        addOrUpdateBuilding();
    });

    function addOrUpdateBuilding() {
        var buildingId = $('#buildingId').val();
        var formData = $('#formEdit').serializeArray();
        var data = {};
        var buildingTypes = [];
        $.each(formData , function (index , v) {
            if(v.name === 'buildingTypes'){
                buildingTypes.push(v.value);
            }else{
                data[''+v.name+''] = v.value;
            }
        });
        data['buildingTypes'] = buildingTypes;
        if(buildingId == ''){
            addBuilding(data);
        }else{
            editBuilding(data , buildingId);
        }
    }
    
    function addBuilding(data) {
        $.ajax({
            url: '${buildingAPI}',
            data: JSON.stringify(data),
            type:'POST',
            contentType: 'application/json',
            dataType: 'json',
            success: function(data) {
                window.location.href = "${buildingURL}?action=EDIT&id="+data.id+"&message=insert_success";
            },
            error: function() {
                window.location.href = "${buildingURL}?action=LIST&message=error_system";
            }
        });
    }
    function editBuilding(data , id) {
        $.ajax({
            url: '${buildingAPI}',
            data: JSON.stringify(data),
            type:'PUT',
            contentType: 'application/json',
            success: function(data) {
                window.location.href = "${buildingURL}?action=EDIT&id="+data.id+"&message=update_success";
            },
            error: function() {
                window.location.href = "${buildingURL}?action=LIST&message=error_system";
            }
        });
    }
</script>

<!-- /.main-content -->
</body>
</html>
<%--
'"+datasucces.id+"'--%>
