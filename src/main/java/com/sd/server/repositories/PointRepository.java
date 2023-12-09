package com.sd.server.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sd.server.DAO.PointDAO;
import com.sd.server.DAO.SegmentDAO;
import com.sd.server.Exceptions.NoSessionException;
import com.sd.server.Exceptions.UnauthorizedUserException;
import com.sd.server.Models.Point;
import com.sd.server.Packages.BasePackage;
import com.sd.server.Packages.data.request.point.*;
import com.sd.server.Packages.data.response.point.FindPointPackageData;
import com.sd.server.Packages.data.response.point.GetPointPackageData;

import java.util.List;

public class PointRepository {
    PointDAO pointDAO = new PointDAO();
    SegmentDAO segmentDAO = new SegmentDAO();
    public BasePackage create(String action, String create_request) throws JsonProcessingException, UnauthorizedUserException, NoSessionException {
        BasePackage<CreatePointRequestData> request = BasePackage.fromJson(create_request, CreatePointRequestData.class);
        Long user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateAdminUser(user_id);
        Point point = new Point(request.getData());
        pointDAO.addPoint(point);
        return new BasePackage(action,false,"Ponto cadastrado com sucesso");
    }

    public BasePackage<GetPointPackageData> get(String action, String get_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<GetPointRequestData> request = BasePackage.fromJson(get_request, GetPointRequestData.class);
        Long user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateAdminUser(user_id);
        List<Point> points = pointDAO.getAllPoints();
        GetPointPackageData response_data = new GetPointPackageData(points);
        return new BasePackage(action,response_data,false,"Sucesso");
    }
    public BasePackage destroy(String action, String destroy_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<DeletePointRequestData> request = BasePackage.fromJson(destroy_request, DeletePointRequestData.class);
        Long adm_id = AuthRepository.getUserId(request.getData().getToken());
        long ponto_id = request.getData().getPoint_id();
        AuthRepository.validateAdminUser(adm_id);
        pointDAO.deletePoint(ponto_id);
        segmentDAO.deleteAllPointSegments(ponto_id);
        return new BasePackage(action,false,"Ponto removido com sucesso");
    }
    public BasePackage<FindPointPackageData> find(String action, String find_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<FindPointRequestData> request = BasePackage.fromJson(find_request, FindPointRequestData.class);
        Long adm_id = AuthRepository.getUserId(request.getData().getToken());
        Long ponto_id = request.getData().getPonto_id();
        AuthRepository.validateAdminUser(adm_id);
        Point point = pointDAO.getPointById(ponto_id);
        FindPointPackageData response_data = new FindPointPackageData(point);
        return new BasePackage<FindPointPackageData>(action,response_data,false,"Sucesso");
    }

    public BasePackage update(String action, String update_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<EditPointRequestData> request = BasePackage.fromJson(update_request, EditPointRequestData.class);
        Long user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateCommonUser(user_id);
        Point point = pointDAO.getPointById(request.getData().getPonto_id());
        point.setName(request.getData().getName());
        point.setObservation(request.getData().getObs());
        pointDAO.updatePoint(point);
        return new BasePackage(action,null,false,"Sucesso");
    }

}
