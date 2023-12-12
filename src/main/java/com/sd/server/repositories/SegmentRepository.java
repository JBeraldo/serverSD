package com.sd.server.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sd.server.DAO.SegmentDAO;
import com.sd.server.Exceptions.NoRouteException;
import com.sd.server.Exceptions.NoSessionException;
import com.sd.server.Exceptions.UnauthorizedUserException;
import com.sd.server.Models.RouteSegment;
import com.sd.server.Models.Segment;
import com.sd.server.Packages.BasePackage;
import com.sd.server.Packages.data.request.segment.*;
import com.sd.server.Packages.data.response.segment.FindSegmentPackageData;
import com.sd.server.Packages.data.response.segment.GetSegmentPackageData;
import com.sd.server.Packages.data.response.segment.RequestRoutePackageData;

import java.util.List;

public class SegmentRepository {

    SegmentDAO segmentDAO = new SegmentDAO();
    public BasePackage create(String action, String create_request) throws JsonProcessingException, UnauthorizedUserException, NoSessionException {
        BasePackage<CreateSegmentRequestData> request = BasePackage.fromJson(create_request, CreateSegmentRequestData.class);
        Long user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateAdminUser(user_id);
        Segment segment = new Segment(request.getData());
        segmentDAO.addSegment(segment);
        return new BasePackage(action,false,"Segmento cadastrado com sucesso");
    }

    public BasePackage<GetSegmentPackageData> get(String action, String get_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<GetSegmentRequestData> request = BasePackage.fromJson(get_request, GetSegmentRequestData.class);
        Long user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateAdminUser(user_id);
        List<Segment> segments = segmentDAO.getAllSegments();
        GetSegmentPackageData response_data = new GetSegmentPackageData(segments);
        return new BasePackage(action,response_data,false,"Sucesso");
    }
    public BasePackage destroy(String action, String destroy_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<DeleteSegmentRequestData> request = BasePackage.fromJson(destroy_request, DeleteSegmentRequestData.class);
        Long adm_id = AuthRepository.getUserId(request.getData().getToken());
        long segment_id = request.getData().getSegment_id();
        AuthRepository.validateAdminUser(adm_id);
        segmentDAO.deleteSegment(segment_id);
        return new BasePackage(action,false,"Segmento removido com sucesso");
    }
    public BasePackage<FindSegmentPackageData> find(String action, String find_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<FindSegmentRequestData> request = BasePackage.fromJson(find_request, FindSegmentRequestData.class);
        Long adm_id = AuthRepository.getUserId(request.getData().getToken());
        Long segment_id = request.getData().getSegment_id();
        AuthRepository.validateAdminUser(adm_id);
        Segment segment = segmentDAO.getSegmentById(segment_id);
        FindSegmentPackageData response_data = new FindSegmentPackageData(segment);
        return new BasePackage<FindSegmentPackageData>(action,response_data,false,"Sucesso");
    }

    public BasePackage update(String action, String update_request) throws JsonProcessingException, NoSessionException, UnauthorizedUserException {
        BasePackage<EditSegmentRequestData> request = BasePackage.fromJson(update_request, EditSegmentRequestData.class);
        Long user_id = AuthRepository.getUserId(request.getData().getToken());
        AuthRepository.validateCommonUser(user_id);
        Segment segment = segmentDAO.getSegmentById(request.getData().getSegment_id());
        segment.setDestination(request.getData().getSegment().getDestination());
        segment.setOrigin(request.getData().getSegment().getOrigin());
        segment.setDirection(request.getData().getSegment().getDirection());
        segment.setDistance(request.getData().getSegment().getDistance());
        segment.setObservation(request.getData().getSegment().getObservation());
        segment.setBlocked(request.getData().getSegment().isBlocked());
        segmentDAO.updateSegment(segment);
        return new BasePackage(action,null,false,"Sucesso");
    }

    public BasePackage<RequestRoutePackageData> findPath(String action, String find_path_request) throws JsonProcessingException, NoRouteException {
        BasePackage<RequestRouteRequestData> request = BasePackage.fromJson(find_path_request, RequestRouteRequestData.class);
        List<RouteSegment> segments = DjikstraRepository.findPath(request.getData().getOrigin(), request.getData().getDestiny());
        segments.get(segments.size() - 1).setObservation("DESTINO");
        RequestRoutePackageData response_data = new RequestRoutePackageData(segments);
        return new BasePackage<RequestRoutePackageData>(action,response_data,false,"Sucesso");
    }

}
