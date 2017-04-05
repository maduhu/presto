/*
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
package com.facebook.presto.cost;

import com.facebook.presto.Session;
import com.facebook.presto.spi.type.Type;
import com.facebook.presto.sql.planner.Symbol;
import com.facebook.presto.sql.planner.iterative.Lookup;
import com.facebook.presto.sql.planner.plan.PlanNode;

import javax.annotation.concurrent.ThreadSafe;

import java.util.Map;

/**
 * Interface of stats calculator.
 *
 * Obtains estimated statistics for output produced by given PlanNode.
 * Implementation may use lookup to compute needed traits for self/source nodes.
 */
@ThreadSafe
public interface StatsCalculator
{
    PlanNodeStatsEstimate calculateStats(
            PlanNode planNode,
            Lookup lookup,
            Session session,
            Map<Symbol, Type> types);
}