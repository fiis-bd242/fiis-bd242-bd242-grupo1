import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/MenuAnalista.css';

const MenuAsesor = () => {
    const navigate = useNavigate();
    const [time, setTime] = useState(new Date());

    useEffect(() => {
        const timer = setInterval(() => {
            setTime(new Date());
        }, 1000);
        return () => clearInterval(timer);
    }, []);

    return (
        <div className="layout-container">
            {/* Sidebar */}
            <aside className="sidebar">
                <div className="sidebar-content">
                    <div className="logo-container">
                        <img
                            src="https://vectorseek.com/wp-content/uploads/2023/09/Yape-App-Logo-Vector.svg-.png"
                            alt="Logo"
                            className="logo"
                            onClick={() => navigate('/menu')}
                        />
                    </div>
                    <div className="user-profile">
                        <div className="avatar"></div>
                        <div className="welcome-text">¡Bienvenido/a, Asesor!</div>
                    </div>
                    <nav className="nav-menu">
                        <button className="nav-button" onClick={() => navigate('/menu/asesor/conversaciones-historial')}>Conversatorio</button>
                        <button className="nav-button" onClick={() => navigate('/menu/asesor/ticketsAsignados')}>Tickets Asignados</button>
                        <button className="nav-button" onClick={() => navigate('/menu/asesor/guia')}>Guía</button>
                    </nav>
                </div>
                <button className="logout-button" onClick={() => navigate('/login')}>
                    Cerrar sesión
                </button>
            </aside>

            {/* Main Content */}
            <main className="main-content">
                <header className="header">
                    <h1 className="page-title">Menú Asesor</h1>
                    <div className="time">{time.toLocaleTimeString()}</div>
                </header>

                {/* Tarjetas */}
                <div className="cards-grid">
                    <div className="card" onClick={() => navigate('/menu/asesor/conversaciones-historial')}>
                        <img src="public\imagenes\pagin1.png" alt="Conversatorio" className="icon" />
                        <h2 className="card-title">Conversatorio</h2>
                        <p className="card-description">Asigna una tipificación a un caso</p>
                    </div>
                    <div className="card" onClick={() => navigate('/menu/asesor/ticketsAsignados')}>
                        <img src="public\imagenes\pagin2.png" alt="Tickets" className="icon" />
                        <h2 className="card-title">Tickets</h2>
                        <p className="card-description">Visualiza todas las asignaciones</p>
                    </div>
                    <div className="card" onClick={() => navigate('/menu/asesor/guia')}>
                        <img src="public\imagenes\pagin3.png" alt="Guía" className="icon" />
                        <h2 className="card-title">Guía</h2>
                        <p className="card-description">Documento de guía de las tipificaciones</p>
                    </div>
                </div>
            </main>
        </div>
    );
};

export default MenuAsesor;
