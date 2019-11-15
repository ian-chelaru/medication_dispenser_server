package services;

import dao.hibernate.HibernatePrescriptionDao;
import entities.Prescription;
import grpc.MedicationServiceOuterClass.Medication;
import grpc.MedicationServiceOuterClass.MedicationRequest;
import grpc.MedicationServiceOuterClass.MedicationResponse;
import io.grpc.stub.StreamObserver;

import java.util.Date;
import java.util.List;

import static grpc.MedicationServiceGrpc.*;
import static grpc.MedicationServiceOuterClass.*;

public class MedicationService extends MedicationServiceImplBase
{
    private HibernatePrescriptionDao prescriptionDao;

    public MedicationService(HibernatePrescriptionDao prescriptionDao)
    {
        this.prescriptionDao = prescriptionDao;
    }

    @Override
    public void getMedication(MedicationRequest request,StreamObserver<MedicationResponse> responseObserver)
    {
        List<Prescription> prescriptionList = prescriptionDao.findByDate(new Date());
        MedicationResponse.Builder response = MedicationResponse.newBuilder();
        Medication.Builder medicationBuilder = Medication.newBuilder();

        for (Prescription prescription : prescriptionList)
        {
            String medicationName = prescription.getMedication().getName();
            String dailyInterval = prescription.getDailyInterval();
            Medication medication = medicationBuilder.setMedicationName(medicationName).setDailyInterval(dailyInterval)
                    .build();
            response.addMedications(medication);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }

    @Override
    public void taken(MedicationTaken request,
            StreamObserver<EmptyMessage> responseObserver)
    {
        String medicationName = request.getMedicationName();
        System.out.println("Patient took medication: " + medicationName);

        responseObserver.onNext(EmptyMessage.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void notTaken(MedicationNotTaken request,
            StreamObserver<EmptyMessage> responseObserver)
    {
        String medicationName = request.getMedicationName();
        System.out.println("Patient did not take medication: " + medicationName);

        responseObserver.onNext(EmptyMessage.newBuilder().build());
        responseObserver.onCompleted();
    }
}
