package com.abg.stationMapping.util;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abg.stationMapping.Exception.StationMappingException;
import com.abg.stationMapping.config.AppProperties;
import com.abg.stationMapping.ecom.dto.Hours;
import com.abg.stationMapping.ecom.dto.LocationResponse;
import com.abg.stationMapping.ecom.dto.SearchListDetails;
import com.abg.stationMapping.entity.ABGStation;
import com.abg.stationMapping.entity.PartnerStation;
import com.abg.stationMapping.logging.LoggingUtil;
import com.abg.stationMapping.response.dto.Address;
import com.abg.stationMapping.response.dto.Day;
import com.abg.stationMapping.response.dto.Days;
import com.abg.stationMapping.response.dto.Domain;
import com.abg.stationMapping.response.dto.HourSlot;
import com.abg.stationMapping.response.dto.Location;
import com.abg.stationMapping.response.dto.MeetGreet;
import com.abg.stationMapping.response.dto.Partner;
import com.abg.stationMapping.response.dto.PartnerStationMapping;
import com.abg.stationMapping.response.dto.SuccessDetails;
import com.abg.stationMapping.response.dto.SuccessStatus;
import com.abg.stationMapping.response.dto.Transaction;
import com.abg.stationMapping.vo.StationMappingVO;

@Component
public class ResponseBuilder {

	@Autowired
    private AppProperties appProperties;
	
	public PartnerStationMapping preparePartnerStationMapping(PartnerStation partnerStation, 
			StationMappingVO stationMappingVO) throws StationMappingException {
		try {

			// LoggingUtil.log("Start : preparePartnerStationMapping", new
			// ArrayList<String>(Arrays.asList( "alex", "brian", "charles")), stationMappingVO,
			// ResponseBuilder.class, Level.INFO);

			LoggingUtil.log("Start : preparePartnerStationMapping", stationMappingVO, ResponseBuilder.class, Level.INFO);

			SuccessDetails successDetails = SuccessDetails.builder().code(Consts.SUCCESS_HTTP_CODE)
					.message(Consts.SUCCESS).details(appProperties.getApiSuccessResponseMessage()).build();

			SuccessStatus successStatus = SuccessStatus.builder().requestTime(stationMappingVO.getRequestTime()).success(successDetails)
					.build();

			Transaction transaction = Transaction.builder().id(stationMappingVO.getUuid()).build();

			Domain domain = Domain.builder().language(appProperties.getLanguage()).build();

			Partner partner = Partner.builder().code(partnerStation.getPartnerCode()).build();

			List<HourSlot> hourSlots;

			hourSlots = partnerStation.getPartnerStationBusinessHours().stream()
					.filter(obj -> Consts.MONDAY_DAY_OF_WEEK.equals(obj.getWorkingDay().toUpperCase()))
					.map(obj -> HourSlot.builder().open(obj.getOpeningHour()).close(obj.getClosingHour()).build())
					.collect(Collectors.toList());
			Day monday = Day.builder().hourSlots(hourSlots).build();

			hourSlots = partnerStation.getPartnerStationBusinessHours().stream()
					.filter(obj -> Consts.TUESDAY_DAY_OF_WEEK.equals(obj.getWorkingDay().toUpperCase()))
					.map(obj -> HourSlot.builder().open(obj.getOpeningHour()).close(obj.getClosingHour()).build())
					.collect(Collectors.toList());
			Day tuesday = Day.builder().hourSlots(hourSlots).build();

			hourSlots = partnerStation.getPartnerStationBusinessHours().stream()
					.filter(obj -> Consts.WEDNESDAY_DAY_OF_WEEK.equals(obj.getWorkingDay().toUpperCase()))
					.map(obj -> HourSlot.builder().open(obj.getOpeningHour()).close(obj.getClosingHour()).build())
					.collect(Collectors.toList());
			Day wednesday = Day.builder().hourSlots(hourSlots).build();

			hourSlots = partnerStation.getPartnerStationBusinessHours().stream()
					.filter(obj -> Consts.THURSDAY_DAY_OF_WEEK.equals(obj.getWorkingDay().toUpperCase()))
					.map(obj -> HourSlot.builder().open(obj.getOpeningHour()).close(obj.getClosingHour()).build())
					.collect(Collectors.toList());
			Day thursday = Day.builder().hourSlots(hourSlots).build();

			hourSlots = partnerStation.getPartnerStationBusinessHours().stream()
					.filter(obj -> Consts.FRIDAY_DAY_OF_WEEK.equals(obj.getWorkingDay().toUpperCase()))
					.map(obj -> HourSlot.builder().open(obj.getOpeningHour()).close(obj.getClosingHour()).build())
					.collect(Collectors.toList());
			Day friday = Day.builder().hourSlots(hourSlots).build();

			hourSlots = partnerStation.getPartnerStationBusinessHours().stream()
					.filter(obj -> Consts.SATURDAY_DAY_OF_WEEK.equals(obj.getWorkingDay().toUpperCase()))
					.map(obj -> HourSlot.builder().open(obj.getOpeningHour()).close(obj.getClosingHour()).build())
					.collect(Collectors.toList());
			Day saturday = Day.builder().hourSlots(hourSlots).build();

			hourSlots = partnerStation.getPartnerStationBusinessHours().stream()
					.filter(obj -> Consts.SUNDAY_DAY_OF_WEEK.equals(obj.getWorkingDay().toUpperCase()))
					.map(obj -> HourSlot.builder().open(obj.getOpeningHour()).close(obj.getClosingHour()).build())
					.collect(Collectors.toList());
			Day sunday = Day.builder().hourSlots(hourSlots).build();

			hourSlots = partnerStation.getPartnerStationBusinessHours().stream()
					.filter(obj -> Consts.HOLIDAY_DAY_OF_WEEK.equals(obj.getWorkingDay().toUpperCase()))
					.map(obj -> HourSlot.builder().open(obj.getOpeningHour()).close(obj.getClosingHour()).build())
					.collect(Collectors.toList());
			Day holiday = Day.builder().hourSlots(hourSlots).build();

			Days days = Days.builder().monday(monday).tuesday(tuesday).wednesday(wednesday).thursday(thursday)
					.friday(friday).saturday(saturday).sunday(sunday).holiday(holiday).build();

			MeetGreet meetGreet = MeetGreet.builder().enabled(partnerStation.isMadEnabled())
					.parking_remarks(partnerStation.getParkingPlace())
					.key_pickup_remarks(partnerStation.getKeyPickupLocation()).build();

			Address address = Address.builder().address_line_1(partnerStation.getAddressLine1())
					.address_line_2(partnerStation.getAddressLine2()).address_line_3(partnerStation.getAddressLine3())
					.city(partnerStation.getCity()).state_name(partnerStation.getStateName())
					.postal_code(partnerStation.getPostalCode()).country_code(partnerStation.getCountryCode())
					.lat(partnerStation.getLat()).lat_long(partnerStation.getLatLong()).build();

			Location partner_location = Location.builder().code(partnerStation.getPartnerCode())
					.name(partnerStation.getStationName()).days(days).meet_greet(meetGreet).address(address)
					.telephone(partnerStation.getTelephone()).build();

			LoggingUtil.log("End : preparePartnerStationMapping : partner : " + stationMappingVO.getPartnerCode(), stationMappingVO,
					ResponseBuilder.class, Level.INFO);

			return PartnerStationMapping.builder().status(successStatus).transaction(transaction).domain(domain)
					.partner(partner).partner_location(partner_location).build();

		} catch (NullPointerException ex) {
			LoggingUtil.log("NullPointerException : preparePartnerStationMapping : " + ex, stationMappingVO,
					ResponseBuilder.class, Level.ERROR);
			throw new StationMappingException(ex);
		} catch (Exception ex) {
			LoggingUtil.log("Exception : preparePartnerStationMapping : " + ex, stationMappingVO, ResponseBuilder.class,
					Level.ERROR);
			throw new StationMappingException(ex);
		}

	}

	public PartnerStationMapping prepareABGStationMapping(PartnerStationMapping partnerStationMapping,
			ABGStation abgStation, LocationResponse locationResponse, StationMappingVO stationMappingVO) {

		LoggingUtil.log("Start : prepareABGStationMapping : ABG Station : " + abgStation.getAbgStationCode(), stationMappingVO,
				ResponseBuilder.class, Level.INFO);
		
		String brand = ""; 
		if(Consts.AVIS_BRAND_ABBR.equalsIgnoreCase(abgStation.getBrand())) {
			brand = Consts.AVIS_BRAND;
		}else if(Consts.BUDGET_BRAND_ABBR.equalsIgnoreCase(abgStation.getBrand())) {
			brand = Consts.BUDGET_BRAND;
		}
		
		SearchListDetails searchListDetails = locationResponse.getSearchListDetails().stream()
				.filter(obj -> abgStation.getAbgStationCode().toUpperCase().equals(obj.getLocationCode())).findFirst()
				.get();

		Day monday = Day.builder().build();
		Day tuesday = Day.builder().build();
		Day wednesday = Day.builder().build();
		Day thursday = Day.builder().build();
		Day friday = Day.builder().build();
		Day saturday = Day.builder().build();
		Day sunday = Day.builder().build();
		Day holiday = Day.builder().build();

		Hours hour;
		Iterator<Hours> itrHour = searchListDetails.getHours().iterator();
		while (itrHour.hasNext()) {
			hour = (Hours) itrHour.next();
			if (Consts.MONDAY_INDEX_DAY_OF_WEEK.equals(hour.getDayOfWeek())) {
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getFirstOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getFirstClose())) {
					monday = monday.toBuilder()
							.hourSlot(HourSlot.builder().open(hour.getFirstOpen()).close(hour.getFirstClose()).build())
							.build();
				}
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getSecondOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getSecondClose())) {
					monday = monday.toBuilder()
							.hourSlot(
									HourSlot.builder().open(hour.getSecondOpen()).close(hour.getSecondClose()).build())
							.build();
				}
			} else if (Consts.TUESDAY_INDEX_DAY_OF_WEEK.equals(hour.getDayOfWeek())) {
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getFirstOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getFirstClose())) {
					tuesday = tuesday.toBuilder()
							.hourSlot(HourSlot.builder().open(hour.getFirstOpen()).close(hour.getFirstClose()).build())
							.build();
				}
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getSecondOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getSecondClose())) {
					tuesday = tuesday.toBuilder()
							.hourSlot(
									HourSlot.builder().open(hour.getSecondOpen()).close(hour.getSecondClose()).build())
							.build();
				}
			} else if (Consts.WEDNESDAY_INDEX_DAY_OF_WEEK.equals(hour.getDayOfWeek())) {
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getFirstOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getFirstClose())) {
					wednesday = wednesday.toBuilder()
							.hourSlot(HourSlot.builder().open(hour.getFirstOpen()).close(hour.getFirstClose()).build())
							.build();
				}
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getSecondOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getSecondClose())) {
					wednesday = wednesday.toBuilder()
							.hourSlot(
									HourSlot.builder().open(hour.getSecondOpen()).close(hour.getSecondClose()).build())
							.build();
				}
			} else if (Consts.THURSDAY_INDEX_DAY_OF_WEEK.equals(hour.getDayOfWeek())) {
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getFirstOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getFirstClose())) {
					thursday = thursday.toBuilder()
							.hourSlot(HourSlot.builder().open(hour.getFirstOpen()).close(hour.getFirstClose()).build())
							.build();
				}
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getSecondOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getSecondClose())) {
					thursday = thursday.toBuilder()
							.hourSlot(
									HourSlot.builder().open(hour.getSecondOpen()).close(hour.getSecondClose()).build())
							.build();
				}
			} else if (Consts.FRIDAY_INDEX_DAY_OF_WEEK.equals(hour.getDayOfWeek())) {
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getFirstOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getFirstClose())) {
					friday = friday.toBuilder()
							.hourSlot(HourSlot.builder().open(hour.getFirstOpen()).close(hour.getFirstClose()).build())
							.build();
				}
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getSecondOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getSecondClose())) {
					friday = friday.toBuilder()
							.hourSlot(
									HourSlot.builder().open(hour.getSecondOpen()).close(hour.getSecondClose()).build())
							.build();
				}
			} else if (Consts.SATURDAY_INDEX_DAY_OF_WEEK.equals(hour.getDayOfWeek())) {
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getFirstOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getFirstClose())) {
					saturday = saturday.toBuilder()
							.hourSlot(HourSlot.builder().open(hour.getFirstOpen()).close(hour.getFirstClose()).build())
							.build();
				}
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getSecondOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getSecondClose())) {
					saturday = saturday.toBuilder()
							.hourSlot(
									HourSlot.builder().open(hour.getSecondOpen()).close(hour.getSecondClose()).build())
							.build();
				}
			} else if (Consts.SUNDAY_INDEX_DAY_OF_WEEK.equals(hour.getDayOfWeek())) {
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getFirstOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getFirstClose())) {
					sunday = sunday.toBuilder()
							.hourSlot(HourSlot.builder().open(hour.getFirstOpen()).close(hour.getFirstClose()).build())
							.build();
				}
				if (!Consts.NOT_WORKING_HOUR.equals(hour.getSecondOpen())
						|| !Consts.NOT_WORKING_HOUR.equals(hour.getSecondClose())) {
					sunday = sunday.toBuilder()
							.hourSlot(
									HourSlot.builder().open(hour.getSecondOpen()).close(hour.getSecondClose()).build())
							.build();
				}
			}

		}

		Days days = Days.builder().monday(monday).tuesday(tuesday).wednesday(wednesday).thursday(thursday)
				.friday(friday).saturday(saturday).sunday(sunday).holiday(holiday).build();

		List<String> vehicleClassCodes = abgStation.getVehicleClassGroups().stream()
				.map(obj -> obj.getVehicleClassCode().getClassCode()).collect(Collectors.toList());

		MeetGreet meetGreet = MeetGreet.builder().enabled(abgStation.isMadEnabled())
				.reservation_lead_time(abgStation.getAdvanceBookingHour()).vehicle_class_codes(vehicleClassCodes)
				.build();

		Address address = Address.builder().address_line_1(searchListDetails.getAddress1())
				.address_line_2(searchListDetails.getAddress2()).city(searchListDetails.getCity())
				.state_name(searchListDetails.getStateName()).postal_code(searchListDetails.getZipCode())
				.country_code(searchListDetails.getCountyCode()).lat(searchListDetails.getLatitude())
				.lat_long(searchListDetails.getLongitude()).build();

		Location car_rental_location = Location.builder().brand(brand)
				.code(searchListDetails.getLocationCode()).name(searchListDetails.getDescription())
				.key_drop(searchListDetails.getKeyDropLoc()).type(searchListDetails.getType()).days(days)
				.meet_greet(meetGreet).address(address).telephone(searchListDetails.getPhoneNumber()).build();

		LoggingUtil.log("End : prepareABGStationMapping : ABG Station : " + abgStation.getAbgStationCode(), stationMappingVO,
				ResponseBuilder.class, Level.INFO);

		return partnerStationMapping.toBuilder().car_rental_location(car_rental_location).build();
	}
}
