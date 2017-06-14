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
package com.facebook.presto.server;

import com.facebook.presto.Session;
import com.facebook.presto.cost.PlanNodeStatsEstimate;
import com.facebook.presto.spi.type.Type;
import com.facebook.presto.sql.planner.Symbol;
import com.facebook.presto.sql.planner.iterative.Lookup;
import com.facebook.presto.sql.planner.plan.PlanNode;
import com.facebook.presto.sql.planner.plan.SemiJoinNode;

import java.util.Map;
import java.util.Optional;

public class SemiJoinStatsRule
        implements com.facebook.presto.cost.ComposableStatsCalculator.Rule
{
    @Override
    public Optional<PlanNodeStatsEstimate> calculate(PlanNode node, Lookup lookup, Session session, Map<Symbol, Type> types)
    {
        if (!(node instanceof SemiJoinNode)) {
            return Optional.empty();
        }
        SemiJoinNode semiJoinNode = (SemiJoinNode) node;
        PlanNodeStatsEstimate sourceStats = lookup.getStats(semiJoinNode.getSource(), session, types);
        return Optional.of(sourceStats.mapOutputRowCount(rowCount -> rowCount * 0.5));
    }
}