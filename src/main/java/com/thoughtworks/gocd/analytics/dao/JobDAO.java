/*
 * Copyright 2020 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thoughtworks.gocd.analytics.dao;

import com.thoughtworks.go.plugin.api.logging.Logger;
import com.thoughtworks.gocd.analytics.mapper.JobMapper;
import com.thoughtworks.gocd.analytics.models.Job;
import org.apache.ibatis.session.SqlSession;

import java.time.ZonedDateTime;
import java.util.List;

public class JobDAO {
    public static final Logger LOG = Logger.getLoggerFor(JobDAO.class);

    protected static JobMapper mapper(SqlSession sqlSession) {
        return sqlSession.getMapper(JobMapper.class);
    }

    public void insertJobs(SqlSession sqlSession, List<Job> jobs) {
        JobMapper mapper = mapper(sqlSession);
        jobs.stream().forEach(mapper::insert);
    }

    public void insertJob(SqlSession sqlSession, Job job) {
        mapper(sqlSession).insert(job);
    }

    public List<Job> all(SqlSession sqlSession, String pipelineName) {
        return mapper(sqlSession).allJobs(pipelineName);
    }

    public List<Job> longestWaitingFor(SqlSession sqlSession, String pipelineName, ZonedDateTime start, ZonedDateTime end, int limit) {
        return mapper(sqlSession).longestWaitingFor(pipelineName, start, end, limit);
    }

    public List<Job> longestWaitingJobsForAgent(SqlSession sqlSession, String agentUUID, ZonedDateTime start, ZonedDateTime end, int limit) {
        return mapper(sqlSession).longestWaitingJobsForAgent(agentUUID, start, end, limit);
    }

    public List<Job> jobHistory(SqlSession sqlSession, String pipeline, String stage, String job) {
        return mapper(sqlSession).jobHistory(pipeline, stage, job);
    }

    public List<Job> jobDurationForAgent(SqlSession sqlSession, String agentUUID, String pipeline, String stage, String job) {
        return mapper(sqlSession).jobDurationForAgent(agentUUID, pipeline, stage, job);
    }

    public void deleteJobRunsPriorTo(SqlSession sqlSession, ZonedDateTime scheduledDate) {
        mapper(sqlSession).deleteJobRunsPriorTo(scheduledDate);
    }
}
