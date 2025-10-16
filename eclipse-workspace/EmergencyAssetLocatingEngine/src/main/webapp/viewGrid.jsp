<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Office Grid</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />

<style>
body {
	background: #eef1f4;
	font-family: 'Segoe UI', sans-serif;
}

.topbar {
	background: #fff;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

.logo {
	width: 38px;
	height: 38px;
	background: #1976d2;
	border-radius: 8px;
}

.container-center {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}

.grid-wrap {
	margin: 40px auto;
	padding: 30px;
	background: #ffffff;
	border-radius: 16px;
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.08);
	display: flex;
	flex-direction: column;
	align-items: center;
	max-width: fit-content;
}

#gridArea {
	display: flex;
	justify-content: center;
	align-items: center;
}

table.office-grid td {
	width: 48px;
	height: 48px;
	text-align: center;
	vertical-align: middle;
	border: 1px solid #e0e0e0;
	font-size: 20px;
	cursor: pointer;
	transition: transform 0.2s ease, box-shadow 0.2s ease;
	border-radius: 6px;
}

table.office-grid td:hover {
	transform: scale(1.15);
	box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.15);
}

td.empty {
	background: #ffffff;
	color: #9aa0a6;
}

td.wall {
	background: gray;
	color: #fff;
}

td.asset-fire {
	background: #ffebee;
	color: #c62828;
	font-weight: bold;
}

td.asset-firstaid {
	background: #e8f5e9;
	color: #0b8043;
	font-weight: bold;
}

td.asset-exit {
	background: #fff7db;
	color: #c05600;
	font-weight: bold;
}

td.user {
	background: #e3f2fd;
	color: #0d47a1;
	font-weight: bold;
}

td.path {
	background: #fff6d6;
	color: #6a4b00;
}

td.selected {
	outline: 3px solid #1976d2;
	box-shadow: 0 0 12px rgba(25, 118, 210, 0.3);
}

.coords {
	font-weight: 700;
	color: #1976d2;
	padding: 6px 12px;
	background: #f1f6ff;
	border-radius: 6px;
}

.legend-box {
	display: inline-flex;
	align-items: center;
	gap: 4px;
	padding: 6px 12px;
	margin: 4px;
	border-radius: 8px;
	font-size: 14px;
	background: #f7f9fc;
	box-shadow: 0px 1px 4px rgba(0, 0, 0, 0.05);
}
</style>

</head>
<body>
	<nav class="topbar py-2">
		<div class="container d-flex align-items-center">
		<div>
            <span class="h5 mb-0 fw-bold">ResQPath</span>
        </div>
			<div class="ms-auto">
				<a class="btn btn-sm btn-outline-secondary me-1"
					href="UserDashboard.jsp">User Dashboard</a> <a
					class="btn btn-sm btn-outline-danger" href="logout">Logout</a>
			</div>

		</div>
	</nav>

	<div class="container text-center mt-4">
		<h3 class="mb-3">
			<b>OFFICE LAYOUT</b>
		</h3>
		<div class="grid-wrap">
			<div id="gridArea">
				<c:out value="${grid}" escapeXml="false" />
			</div>

			<div class="mt-3">
				Hovered Coordinates: <span id="coords" class="coords">none</span>
			</div>

			<hr class="mt-4">
			<div class="small text-muted text-start">
				<span class="legend-box empty">⬜ Empty</span> <span
					class="legend-box wall">⬛ Wall</span> <span
					class="legend-box asset-fire">🧯 Fire Extinguisher</span> <span
					class="legend-box asset-firstaid">🏥 FirstAid</span> <span
					class="legend-box asset-exit">🚪 Exit</span> <span
					class="legend-box user">🧍 You</span> <span class="legend-box path">➡️
					Path</span>
			</div>


			<div class="mt-3">
				<a class="btn btn-link" href="javascript:history.back()">Back</a>
			</div>
		</div>
	</div>
	<script>
document.addEventListener('DOMContentLoaded', function () {
    const coordsSpan = document.getElementById('coords');

    // click highlight (delegate in case grid is re-rendered)
    document.addEventListener('mouseover', function (e) {
        const td = e.target.closest('td.grid-cell');
        if (!td) return;
        document.querySelectorAll('.office-grid td').forEach(c => c.classList.remove('selected'));
        td.classList.add('selected');
        const x = td.getAttribute('data-x');
        const y = td.getAttribute('data-y');
        coordsSpan.textContent = '(' + x + ',' + y + ')';
    });
    function getDirectionArrow(from, to) {
        if (!from || !to) return '<span style="opacity:0.9;">·</span>';
        // ensure datasets exist
        const fx = from.dataset.x, fy = from.dataset.y;
        const tx = to.dataset.x, ty = to.dataset.y;
        if (fx == null || fy == null || tx == null || ty == null) return '<span style="opacity:0.9;">·</span>';
        const dx = Number(tx) - Number(fx);
        const dy = Number(ty) - Number(fy);
        if (dx === -1 && dy === 0) return '<b>↑</b>';
        if (dx === 1 && dy === 0) return '<b>↓</b>';
        if (dx === 0 && dy === -1) return '<b>←</b>';
        if (dx === 0 && dy === 1) return '<b>→</b>';
        return '<span style="opacity:0.9;">·</span>';
    }

    // animation state
    let step = 0;
    const delayMs = 300; // adjust speed here

    function animatePath() {
        // select path cells with data-path-index and sort by that index (numeric)
        const raw = Array.from(document.querySelectorAll('td.path[data-path-index]'));
        if (raw.length === 0) {
            // nothing to animate: clear any leftover arrows
            document.querySelectorAll('td.path').forEach(td => td.innerHTML = '');
            setTimeout(animatePath, 1000);
            return;
        }
        const pathCells = raw.sort((a,b) => Number(a.dataset.pathIndex) - Number(b.dataset.pathIndex));

        // reset all intermediate cells (except last target) to base icon (empty)
        pathCells.forEach((td, idx) => {
        	td.style.visibility = 'visible';
        	td.innerHTML = '<span style="opacity:0.9;">·</span>'; // subtle dot indicating path
           
        });

        // keep the final asset cell (target) visible — it's NOT in pathCells because we skipped last during server render
        // now show arrow at current step (only for intermediate cells)
        if (step < pathCells.length) {
            const cur = pathCells[step];
            const nxt = pathCells[step + 1] || null; // next may be null (last intermediate)
            if (nxt) {
                cur.innerHTML = getDirectionArrow(cur, nxt);
            } else {
                // last intermediate: arrow should point to the target asset (look up target by DOM coordinates)
                // find the DOM cell at next coordinates using dataset of the final element in path (we expect target not to be a .path)
                // compute arrow direction to the asset: try to find a cell adjacent with matching data-x/y
                // we'll search 4-neighbors for a non-path cell (asset)
                const px = Number(cur.dataset.x);
                const py = Number(cur.dataset.y);
                const neighbors = [
                    [px-1, py],
                    [px+1, py],
                    [px, py-1],
                    [px, py+1]
                ];
                let arrowToAsset = '<b>→</b>'; // fallback
                for (const [nx, ny] of neighbors) {
                    const neighborTd = document.querySelector(`td[data-x="${nx}"][data-y="${ny}"]`);
                    if (neighborTd && !neighborTd.classList.contains('path')) {
                        // neighbor is the asset or empty cell; compute arrow from cur->neighbor
                        arrowToAsset = getDirectionArrow(cur, neighborTd);
                        break;
                    }
                }
                cur.innerHTML = arrowToAsset;
            }
            cur.style.visibility = 'visible';
            step++;
        } else {
            // loop: clear and restart
            step = 0;
        }

        setTimeout(animatePath, delayMs);
    }

    // start animation loop
    animatePath();

});
</script>


</body>
</html>
