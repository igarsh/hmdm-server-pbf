/*
 *
 * Headwind MDM: Open Source Android MDM Software
 * https://h-mdm.com
 *
 * Copyright (C) 2019 Headwind Solutions LLC (http://h-sms.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.hmdm.plugins.geolocation.rest;

import com.hmdm.plugin.service.PluginStatusCache;
import com.hmdm.rest.json.DeviceLocation;
import com.hmdm.rest.json.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * <p>A resource to be used for managing the <code>Geolocation</code> plugin data for customer account associated
 * with current user.</p>
 *
 * @author isv
 */
@Singleton
@Path("/plugins/geolocation")
@Api(tags = {"Geolocation plugin"})
public class GeolocationResource {

    private static final Logger logger = LoggerFactory.getLogger(GeolocationResource.class);

    private PluginStatusCache pluginStatusCache;

    /**
     * <p>A constructor required by swagger.</p>
     */
    public GeolocationResource() {
    }

    /**
     * <p>Constructs new <code>GeolocationResource</code> instance. This implementation does nothing.</p>
     */
    @Inject
    public GeolocationResource(/*GeolocationDAO geolocationDAO,
                             UnsecureDAO unsecureDAO,
                             DeviceDAO deviceDAO,
                             PushService pushService,*/
            PluginStatusCache pluginStatusCache) {
      /*  this.geolocationDAO = geolocationDAO;
        this.unsecureDAO = unsecureDAO;
        this.deviceDAO = deviceDAO;
        this.pushService = pushService;*/
        this.pluginStatusCache = pluginStatusCache;
    }

    // =================================================================================================================


    @ApiOperation(
            value = "Locate device",
            notes = "Gets the location of device",
            response = DeviceLocation.class,
            authorizations = {@Authorization("Bearer Token")}
    )
    @GET
    @Path("/private/locate/{deviceNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response locateDevice(@PathParam("deviceNumber") String deviceNumber) {
        try {
            DeviceLocation dl = new DeviceLocation();
            dl.setLat(18.445139);
            dl.setLon(1.407087);
            dl.setTs(new java.util.Date().getTime());
            return Response.OK(dl);
        } catch (Exception e) {
            logger.error("Failed to locate device due to unexpected error.", e);
            return Response.INTERNAL_ERROR();
        }
    }
}
