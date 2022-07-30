/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.sb.web.controller;

import cc.altius.sb.service.TestSessionsService;
import cc.altius.sb.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author yogesh
 */
@Controller
public class SchedulerController {

    @Autowired
    private TestSessionsService sessionsService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//    @RequestMapping(value = "/home/test.htm")
//    @Scheduled(cron = "00 */05 * * * *")

    @Scheduled(cron = "00 00 14 * * *")
    public void deleteLastSortOrder() {
        logger.info(LogUtils.buildStringForSystemLog("Scheduler started for delet last sorder is less than 0 "));
        try {
            logger.info(LogUtils.buildStringForSystemLog("Scheduler active"));
            int rows = this.sessionsService.deleteLastSorder();

            logger.info(LogUtils.buildStringForSystemLog("Scheduler  not active"));

        } catch (Exception e) {
            logger.error(LogUtils.buildStringForSystemLog(e));
        }
        logger.info(LogUtils.buildStringForSystemLog("Scheduler completed for CsatReportFromDialer"));
    }

    @Scheduled(cron = "0 42 20 * * *")
    @Transactional
    @RequestMapping(value = "/getPopulationStatsInDatabase.htm")
    public void updatePopulationStatsInDatabase1() {
        logger.info(LogUtils.buildStringForSystemLog("Scheduler started for population stats "));
        try { 
           this.sessionsService.updatePopulationStatsInDatabase();
        } catch (Exception e) {
            logger.error(LogUtils.buildStringForSystemLog(e));
        }
        logger.info(LogUtils.buildStringForSystemLog("Scheduler completed for population stats"));
    }

}
