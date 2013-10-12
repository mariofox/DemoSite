--
-- The Archetype is configured with "hibernate.hbm2ddl.auto" value="create-drop" in "persistence.xml".
--
-- This will cause hibernate to populate the database when the application is started by processing the files that
-- were configured in the hibernate.hbm2ddl.import_files property.
--
-- This file is responsible for loading the the sitemap configuration data used in the Archetype.   Implementers can change this file
-- to load their initial sitemap configuration.
--

INSERT INTO BLC_MODULE_CONFIGURATION (MODULE_CONFIG_ID,CONFIG_TYPE,IS_DEFAULT,MODULE_NAME,MODULE_PRIORITY,ACTIVE_START_DATE) VALUES (-1,'SITE_MAP',TRUE,'SITE_MAP',100,CURRENT_TIMESTAMP);
INSERT INTO BLC_SITE_MAP_CONFIG (MODULE_CONFIG_ID,SITE_MAP_FILE_NAME) VALUES (-1,'http://www.heatclinic.com/sitemap.xml');
INSERT INTO BLC_SITE_MAP_GEN_CONFIG (SITE_MAP_GEN_CONFIG_ID,DISABLED,CHANGE_FREQ_TYPE,SITE_MAP_GENERATOR_TYPE,SITE_MAP_PRIORITY,SITE_MAP_CONFIG) VALUES (-1,FALSE,'HOURLY','CATEGORY','POINT5',-1);
INSERT INTO BLC_SITE_MAP_URL_ENTRY (SITE_MAP_URL_ENTRY_ID,LAST_MODIFIED,LOCATION,CHANGE_FREQ_TYPE,SITE_MAP_PRIORITY,SITE_MAP_GEN_CONFIG) VALUES (-1,CURRENT_TIMESTAMP,'http://www.heatclinic.com/','HOURLY','POINT5',-1);
